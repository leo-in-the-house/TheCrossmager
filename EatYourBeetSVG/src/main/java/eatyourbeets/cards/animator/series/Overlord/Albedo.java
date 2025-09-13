package eatyourbeets.cards.animator.series.Overlord;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.SadisticPower;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.monsters.EnemyIntent;
import eatyourbeets.powers.animator.EnchantedArmorPlayerPower;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.TargetHelper;

public class Albedo extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Albedo.class)
            .SetAttack(2, CardRarity.RARE)
            .SetSeriesFromClassPackage();

    public Albedo()
    {
        super(DATA);

        Initialize(2, 0, 3, 1);
        SetUpgrade(0, 0, 3);

        SetAffinity_Pink(1, 0, 1);
        SetAffinity_Violet(1, 0, 1);

        SetEthereal(true);
    }

    @Override
    public AbstractAttribute GetDamageInfo()
    {
        return super.GetDamageInfo().AddMultiplier(magicNumber);
    }

    @Override
    protected void OnUpgrade()
    {
        SetEthereal(false);
    }

    @Override
    public void triggerOnAffinitySeal(boolean reshuffle)
    {
        super.triggerOnAffinitySeal(reshuffle);

        GameActions.Bottom.Flash(this);
        GameActions.Bottom.StackPower(new SadisticPower(player, secondaryValue));
    }

    @Override
    public void triggerOnManualDiscard()
    {
        super.triggerOnManualDiscard();

        GameActions.Bottom.Flash(this);
        GameActions.Bottom.StackPower(new SadisticPower(player, secondaryValue));
    }

    @Override
    public void OnDrag(AbstractMonster m)
    {
        super.OnDrag(m);

        int debuffAmount = CalculateCommonDebuffAmount();

        if (debuffAmount > 0) {
            for (EnemyIntent intent : GameUtilities.GetIntents())
            {
                intent.AddPlayerEnchantedArmor(1);
            }
        }
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);

        for (int i=0; i<magicNumber; i++) {
            if (i%3 == 0) {
                GameActions.Bottom.DealDamage(this, m, AttackEffects.SLASH_VERTICAL);
            }
            else if (i%3 == 1) {
                GameActions.Bottom.DealDamage(this, m, AttackEffects.SLASH_HORIZONTAL);
            }
            else {
                GameActions.Bottom.DealDamage(this, m, AttackEffects.SLASH_HEAVY);
            }
        }

        int debuffAmount = CalculateCommonDebuffAmount();

        if (debuffAmount >= 4) {
            GameActions.Bottom.StackPower(new EnchantedArmorPlayerPower(p, 1));
        }
    }

    private int CalculateCommonDebuffAmount() {
        return GameUtilities.GetCommonDebuffs(TargetHelper.Enemies()).size();
    }
}