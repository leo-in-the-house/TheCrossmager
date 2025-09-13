package eatyourbeets.cards.animator.series.LegendOfHeroesTrails;

import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Campanella extends AnimatorCard {
    public static final EYBCardData DATA = Register(Campanella.class)
            .SetPower(3, CardRarity.UNCOMMON)
            .SetSeriesFromClassPackage();

    public Campanella() {
        super(DATA);

        Initialize(0, 0, 0);
        SetUpgrade(0, 0, 0);
        SetCostUpgrade(-1);

        SetDelayed(true);

        SetAffinity_Blue(1);
        SetAffinity_Pink(1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.StackPower(new CampanellaPower(p, 1));
    }

    public static class CampanellaPower extends AnimatorPower {
        public CampanellaPower(AbstractCreature owner, int amount) {
            super(owner, Campanella.DATA);
            Initialize(amount);
        }

        @Override
        public void updateDescription() {
            description = FormatDescription(0, amount);
        }

        @Override
        public void onUseCard(AbstractCard card, UseCardAction action)
        {
            if ((card.type == AbstractCard.CardType.POWER) && !card.isInAutoplay)
            {
                flash();

                AbstractMonster m = null;
                if (action.target != null)
                {
                    m = (AbstractMonster) action.target;
                }

                for (int i=0; i<amount; i++) {
                    GameActions.Top.PlayCopy(card, m);
                }
            }
        }
    }
}