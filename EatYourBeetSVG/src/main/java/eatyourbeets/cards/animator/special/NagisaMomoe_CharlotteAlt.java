package eatyourbeets.cards.animator.special;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.series.MadokaMagica.NagisaMomoe;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBAttackType;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.modifiers.DamageModifiers;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.effects.VFX;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameEffects;
import eatyourbeets.utilities.GameUtilities;

public class NagisaMomoe_CharlotteAlt extends AnimatorCard
{
    public static final EYBCardData DATA = Register(NagisaMomoe_CharlotteAlt.class)
            .SetAttack(3, CardRarity.SPECIAL, EYBAttackType.Normal)
            .SetSeries(NagisaMomoe.DATA.Series);

    public NagisaMomoe_CharlotteAlt()
    {
        super(DATA);

        Initialize(30, 0, 6);
        SetUpgrade(12, 0);

        SetAffinity_Blue(2, 0, 1);
        SetAffinity_Black(1, 0, 1);

        SetRetain(true);
    }


    @Override
    public void triggerWhenDrawn() {
        super.triggerWhenDrawn();

        if (CombatStats.TryActivateSemiLimited(cardID)) {
            GameActions.Bottom.PurgeFromPile(name, 1, player.exhaustPile)
                .SetFilter(card -> card.type == CardType.CURSE)
                .SetOptions(true, true)
                .AddCallback(cards -> {
                    if (cards.size() > 0) {
                        GameActions.Top.ModifyAllInstances(uuid, c ->
                        {
                            DamageModifiers.For(c).Add(damage);
                            c.flash();
                        });
                    }
                });
        }
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.DealDamage(this, m, AttackEffects.BITE)
        .SetDamageEffect(e ->
        {
            GameEffects.List.BorderFlash(Color.RED);
            return GameEffects.List.Add(VFX.Hemokinesis(player.hb, e.hb)).duration * 0.1f;
        })
        .AddCallback(info, (info2, enemy) ->
        {
            if (GameUtilities.IsFatal(enemy, true))
            {
                GameActions.Top.Heal(magicNumber);
                GameUtilities.ModifyCostForCombat(this, -1, true);
                GameActions.Delayed.MoveCard(this, player.hand)
                .ShowEffect(false, false);
            }
        });
    }
}