package eatyourbeets.cards.animator.series.GATE;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Bozes extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Bozes.class)
            .SetAttack(3, CardRarity.UNCOMMON)
            .SetSeriesFromClassPackage();

    public Bozes()
    {
        super(DATA);

        Initialize(7, 0, 1);
        SetUpgrade(0, 0, 0);

        SetCostUpgrade(-1);

        SetAffinity_White(2);
        SetAffinity_Red(1);

        SetExhaust(true);
        SetEthereal(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.DealDamage(this, m, AttackEffects.SLASH_VERTICAL);
        GameActions.Bottom.StackPower(new BozesPower(p, 1));
    }

    public class BozesPower extends AnimatorPower
    {
        public BozesPower(AbstractCreature owner, int amount)
        {
            super(owner, Bozes.DATA);

            Initialize(amount);
        }

        @Override
        public void onAfterCardPlayed(AbstractCard card)
        {
            super.onAfterCardPlayed(card);

            if (card.type.equals(CardType.ATTACK))
            {
                GameActions.Bottom.GainStrength(amount);
                flashWithoutSound();
            }
        }

        @Override
        public void atStartOfTurn()
        {
            super.atStartOfTurn();

            RemovePower();
        }
    }
}