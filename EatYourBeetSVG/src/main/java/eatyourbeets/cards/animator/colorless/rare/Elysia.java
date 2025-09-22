package eatyourbeets.cards.animator.colorless.rare;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.special.Elysia_Herrscher;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardSeries;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Elysia extends AnimatorCard {
    public static final EYBCardData DATA = Register(Elysia.class)
            .SetPower(1, CardRarity.RARE)
            .SetColor(CardColor.COLORLESS)
            .SetSeries(CardSeries.HonkaiImpact3rd)
            .PostInitialize(data -> data.AddPreview(new Elysia_Herrscher(), false));

    public Elysia() {
        super(DATA);

        Initialize(0, 0, 3);
        SetUpgrade(0, 0, 0);
        SetCostUpgrade(-1);

        SetAffinity_White(1);
        SetAffinity_Teal(1);
        SetAffinity_Pink(1);

        SetInnate(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.StackPower(new ElysiaPower(p, magicNumber));
    }

    public static class ElysiaPower extends AnimatorPower {
        public ElysiaPower(AbstractCreature owner, int amount) {
            super(owner, Elysia.DATA);
            Initialize(amount);
        }

        @Override
        public void updateDescription() {
            description = FormatDescription(0, amount);
        }


        @Override
        public void atStartOfTurnPostDraw() {
            super.atStartOfTurnPostDraw();

            GameActions.Bottom.Scry(amount)
                .AddCallback(c -> {
                    if (player.drawPile.size() == 12) {
                        GameActions.Top.MakeCardInHand(new Elysia_Herrscher());
                        RemovePower();
                    }
                });
        }
    }
}