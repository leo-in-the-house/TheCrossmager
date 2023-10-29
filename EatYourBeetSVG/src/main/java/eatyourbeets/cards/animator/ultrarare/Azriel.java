package eatyourbeets.cards.animator.ultrarare;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard_UltraRare;
import eatyourbeets.cards.base.CardSeries;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.powers.replacement.PlayerFlightPower;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Azriel extends AnimatorCard_UltraRare
{
    public static final EYBCardData DATA = Register(Azriel.class)
            .SetPower(3, CardRarity.SPECIAL)
            .SetColor(CardColor.COLORLESS)
            .SetSeries(CardSeries.NoGameNoLife);

    public Azriel()
    {
        super(DATA);

        Initialize(0, 0, 2);
        SetUpgrade(0, 0, 0);

        SetAffinity_Black(2);
        SetAffinity_Pink(2);

        SetEthereal(true);
        SetDelayed(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.StackPower(new PlayerFlightPower(p, 2));

        GameActions.Bottom.StackPower(new AzrielPower(p, 1, upgraded));
    }

    public class AzrielPower extends AnimatorPower
    {
        boolean upgraded;
        public AzrielPower(AbstractCreature owner, int amount, boolean upgraded)
        {
            super(owner, Azriel.DATA);
            this.upgraded = upgraded;

            Initialize(amount);
        }

        @Override
        public void updateDescription()
        {
            this.description = FormatDescription(upgraded ? 1 : 0, amount);
        }

        @Override
        public void onPlayCard(AbstractCard card, AbstractMonster m) {
            super.onPlayCard(card, m);

            if (card.costForTurn == 0) {
                if (upgraded) {
                    GameActions.Bottom.Draw(1)
                        .SetFilter(c -> c.costForTurn == 0, false);
                } else {
                    GameActions.Bottom.Draw(1);
                }
            }
        }
    }
}