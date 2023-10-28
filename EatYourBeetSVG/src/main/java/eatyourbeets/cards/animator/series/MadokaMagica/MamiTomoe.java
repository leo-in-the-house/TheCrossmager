package eatyourbeets.cards.animator.series.MadokaMagica;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.ShakeScreenAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.MindblastEffect;
import eatyourbeets.cards.animator.curse.special.Curse_GriefSeed;
import eatyourbeets.cards.animator.special.MamiTomoe_Candeloro;
import eatyourbeets.cards.base.*;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.resources.GR;
import eatyourbeets.ui.common.EYBCardPopupActions;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameEffects;
import eatyourbeets.utilities.GameUtilities;

public class MamiTomoe extends AnimatorCard
{
    public static final EYBCardData DATA = Register(MamiTomoe.class)
            .SetAttack(-1, CardRarity.UNCOMMON, EYBAttackType.Ranged, EYBCardTarget.ALL)
            .SetSeriesFromClassPackage()
            .PostInitialize(data ->
            {
                data.AddPopupAction(new EYBCardPopupActions.MadokaMagica_Witch(MamiTomoe_Candeloro.DATA));
                data.AddPreview(new MamiTomoe_Candeloro(), true);
                data.AddPreview(new Curse_GriefSeed(), false);
            });

    public MamiTomoe()
    {
        super(DATA);

        Initialize(11, 0, 0);
        SetUpgrade(3, 0, 0);

        SetAffinity_Yellow(1, 0, 1);
        SetAffinity_White(1);

        SetFading(true);
        SetMultiDamage(true);
    }

    @Override
    public void triggerOnExhaust()
    {
        super.triggerOnExhaust();

        GameActions.Bottom.Draw(magicNumber)
                .SetFilter(c -> c.costForTurn == 0, false);
    }

    @Override
    public AbstractAttribute GetDamageInfo()
    {
        return super.GetDamageInfo().AddMultiplier(GameUtilities.GetPotentialXCostEnergy(this));
    }

    @Override
    public void triggerOnManualDiscard()
    {
        super.triggerOnManualDiscard();

        GameActions.Bottom.Draw(magicNumber)
                .SetFilter(c -> c.costForTurn == 0, false);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);

        int stacks = GameUtilities.UseXCostEnergy(this);

        if (stacks > 0) {
            MamiTomoe other = (MamiTomoe) makeStatEquivalentCopy();
            other.energyOnUse = stacks;
            other.tags.remove(GR.Enums.CardTags.IGNORE_PEN_NIB);
            CombatStats.onStartOfTurnPostDraw.Subscribe(other);
        }
    }

    @Override
    public void OnStartOfTurnPostDraw()
    {
        applyPowers();
        int xCostEnergy = GameUtilities.GetXCostEnergy(this);

        if (xCostEnergy > 0) {
            GameEffects.Queue.ShowCardBriefly(makeStatEquivalentCopy());

            GameActions.Bottom.SFX("ATTACK_HEAVY");
            GameActions.Bottom.VFX(new MindblastEffect(player.dialogX, player.dialogY, player.flipHorizontal), 0.05f * xCostEnergy);

            for (int i=0; i<xCostEnergy; i++) {
                GameActions.Bottom.DealDamageToAll(this, AbstractGameAction.AttackEffect.NONE);
            }

            GameActions.Bottom.Add(new ShakeScreenAction(0.5f, ScreenShake.ShakeDur.LONG, ScreenShake.ShakeIntensity.MED));
        }

        GameUtilities.RemoveDamagePowers();
        CombatStats.onStartOfTurnPostDraw.Unsubscribe(this);
    }
}
