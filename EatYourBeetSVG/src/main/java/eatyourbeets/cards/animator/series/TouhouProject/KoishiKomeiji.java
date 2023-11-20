package eatyourbeets.cards.animator.series.TouhouProject;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.RandomizedList;

public class KoishiKomeiji extends AnimatorCard {
    public static final EYBCardData DATA = Register(KoishiKomeiji.class)
            .SetPower(2, CardRarity.UNCOMMON)
            .SetSeriesFromClassPackage();

    public KoishiKomeiji() {
        super(DATA);

        Initialize(0, 2, 4);
        SetCostUpgrade(-1);

        SetAffinity_Pink(1);
        SetAffinity_Black(1);
    }

    @Override
    public AbstractAttribute GetBlockInfo()
    {
        return super.GetBlockInfo().AddMultiplier(magicNumber);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        for (int i=0; i<magicNumber; i++) {
            GameActions.Bottom.GainBlock(block);
        }

        GameActions.Bottom.StackPower(new KoishiKomeijiPower(p, 1));
    }

    public static class KoishiKomeijiPower extends AnimatorPower {
        public KoishiKomeijiPower(AbstractCreature owner, int amount) {
            super(owner, KoishiKomeiji.DATA);
            Initialize(amount);
        }

        @Override
        public void updateDescription() {
            description = FormatDescription(0, amount);
        }

        @Override
        public void atStartOfTurn() {
            super.atStartOfTurn();

            RandomizedList<AbstractCard> possibleCards = new RandomizedList<>();

            for (AbstractCard card : player.hand.group) {
                if (GameUtilities.HasAttackOrBlockMultiplier(card)) {
                    possibleCards.Add(card);
                }
            }

            if (possibleCards.Size() > 0) {
                AbstractCard card = possibleCards.Retrieve(rng);

                if (card != null) {
                    GameActions.Bottom.SpendEnergy(card).AddCallback(amount -> {
                        GameActions.Top.PlayCard(card, GameUtilities.GetRandomEnemy(true))
                            .AddCallback(c -> {
                                GameActions.Top.GainEnergy(1);
                            });
                    });
                }
            }
        }
    }
}