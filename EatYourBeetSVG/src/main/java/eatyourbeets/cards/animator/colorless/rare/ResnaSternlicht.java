package eatyourbeets.cards.animator.colorless.rare;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardSeries;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.modifiers.CostModifiers;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class ResnaSternlicht extends AnimatorCard {
    public static final EYBCardData DATA = Register(ResnaSternlicht.class)
            .SetPower(2, CardRarity.RARE)
            .SetColor(CardColor.COLORLESS)
            .SetSeries(CardSeries.Atelier);

    public ResnaSternlicht() {
        super(DATA);

        Initialize(0, 0, 1);
        SetUpgrade(0, 0, 1);

        SetAffinity_Blue(1);
        SetAffinity_Pink(1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.StackPower(new ResnaSternlichtPower(p, magicNumber));
    }

    public static class ResnaSternlichtPower extends AnimatorPower {
        public ResnaSternlichtPower(AbstractCreature owner, int amount) {
            super(owner, ResnaSternlicht.DATA);
            Initialize(amount);
        }

        @Override
        public void updateDescription()
        {
            description = FormatDescription(0, amount);
        }

        @Override
        public void atStartOfTurnPostDraw() {
            super.atStartOfTurnPostDraw();

            GameActions.Bottom.MakeCardInHand(GetRandomUncommonOrRare().makeCopy())
                    .Repeat(amount)
                    .AddCallback(card -> {
                        CostModifiers.For(card).Set(0);
                    });
        }

        private AbstractCard GetRandomUncommonOrRare() {
            if (rng.randomBoolean()) {
                return AbstractDungeon.getCard(CardRarity.RARE);
            }
            else {
                return AbstractDungeon.getCard(CardRarity.UNCOMMON);
            }
        }
    }
}