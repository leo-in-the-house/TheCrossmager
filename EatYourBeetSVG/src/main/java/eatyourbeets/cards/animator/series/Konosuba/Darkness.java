package eatyourbeets.cards.animator.series.Konosuba;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import eatyourbeets.cards.animator.special.Darkness_Adrenaline;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.utilities.GameActions;

public class Darkness extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Darkness.class)
            .SetPower(1, CardRarity.UNCOMMON)
            .SetSeriesFromClassPackage()
            .PostInitialize(data -> data.AddPreview(new Darkness_Adrenaline(), true));

    public Darkness()
    {
        super(DATA);

        Initialize(0, 0, 2, 2);
        SetUpgrade(0, 0, 1);

        SetAffinity_White(1, 0, 0);
        SetAffinity_Black(1, 0, 0);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.StackPower(new PlatedArmorPower(p, magicNumber));
        GameActions.Bottom.StackPower(new DarknessPower(p, secondaryValue, upgraded));
    }

    public static class DarknessPower extends AnimatorPower
    {
        final private boolean upgraded;

        public DarknessPower(AbstractPlayer owner, int amount, boolean upgraded)
        {
            super(owner, Darkness.DATA);
            this.upgraded = upgraded;

            Initialize(amount);
        }

        @Override
        public void wasHPLost(DamageInfo info, int damageAmount)
        {
            super.wasHPLost(info, damageAmount);

            if (amount > 0 && info.type != DamageInfo.DamageType.HP_LOSS && damageAmount > 0)
            {
                GameActions.Bottom.MakeCardInDrawPile(new Darkness_Adrenaline())
                        .SetUpgrade(upgraded, true);
                if ((amount -= 1) <= 0)
                {
                    RemovePower();
                }

                this.flash();
            }
        }

        @Override
        public void updateDescription() {
            if (upgraded) {
                description = FormatDescription(1, amount);
            }
            else {
                description = FormatDescription(0, amount);
            }
        }
    }
}