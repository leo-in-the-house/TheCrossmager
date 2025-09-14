package eatyourbeets.cards.animator.ultrarare;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard_UltraRare;
import eatyourbeets.cards.base.CardSeries;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Grandmaster extends AnimatorCard_UltraRare {
    public static final EYBCardData DATA = Register(Grandmaster.class)
            .SetPower(3, CardRarity.SPECIAL)
            .SetColor(CardColor.COLORLESS)
            .SetSeries(CardSeries.LegendOfHeroesTrails)
            .PostInitialize(data ->
            {
                data.AddPreview(new Grandmaster_Phase1(), true);
                data.AddPreview(new Grandmaster_Phase2(), true);
                data.AddPreview(new Grandmaster_Phase3(), true);
                data.AddPreview(new Grandmaster_Phase4(), true);
            });

    public Grandmaster() {
        super(DATA);

        Initialize(0, 0, 0);
        SetUpgrade(0, 0, 0);
        SetCostUpgrade(-1);

        SetAffinity_Teal(2);
        SetAffinity_Black(2);
        SetAffinity_Pink(2);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.StackPower(new GrandmasterPower(p, 1));
    }

    public static class GrandmasterPower extends AnimatorPower {

        public GrandmasterPower(AbstractCreature owner, int amount) {
            super(owner, Grandmaster.DATA);
            Initialize(amount);
        }

        @Override
        public void atStartOfTurnPostDraw() {
            super.atStartOfTurnPostDraw();

            flash();

            GameActions.Top.MakeCardInHand(new Grandmaster_Phase1())
                    .Repeat(amount);
            RemovePower();
        }
        @Override
        public void updateDescription() {
            description = FormatDescription(0, amount);
        }
    }
}