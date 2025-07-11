package eatyourbeets.cards.animator.series.Elsword;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.cards.base.attributes.TempHPAttribute;
import eatyourbeets.effects.SFX;
import eatyourbeets.monsters.EnemyIntent;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.TargetHelper;

public class Laby extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Laby.class)
            .SetPower(2, CardRarity.UNCOMMON)
            
            .SetSeriesFromClassPackage();

    public Laby()
    {
        super(DATA);

        Initialize(0, 0, 4, 2);
        SetUpgrade(0, 0, 6, 0);

        SetAffinity_Blue(1);
        SetAffinity_Pink(1);
        SetAffinity_Violet(1);

        SetDelayed(true);
        SetEthereal(true);
    }

    @Override
    protected void OnUpgrade()
    {
        SetDelayed(false);
        SetEthereal(false);
    }

    @Override
    public void OnDrag(AbstractMonster m)
    {
        super.OnDrag(m);

        for (EnemyIntent intent : GameUtilities.GetIntents())
        {
            intent.AddPlayerEnchantedArmor(secondaryValue);
        }
    }

    @Override
    public AbstractAttribute GetSpecialInfo()
    {
        return TempHPAttribute.Instance.SetCard(this, true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.GainTemporaryHP(magicNumber);
        GameActions.Bottom.GainEnchantedArmor(secondaryValue);
        GameActions.Bottom.StackPower(new LabyPower(p, 1));
    }

    public static class LabyPower extends AnimatorPower
    {
        public LabyPower(AbstractCreature owner, int amount)
        {
            super(owner, Laby.DATA);

            Initialize(amount);
        }

        @Override
        public void atStartOfTurn()
        {
            super.atStartOfTurn();

            GameActions.Bottom.ApplyConstricted(TargetHelper.AllCharacters(), amount)
            .ShowEffect(false, true);
            SFX.Play(SFX.POWER_CONSTRICTED);
            this.flashWithoutSound();
        }
    }
}