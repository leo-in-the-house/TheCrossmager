package eatyourbeets.cards.animator.ultrarare;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.Lightning;
import eatyourbeets.cards.base.AnimatorCard_UltraRare;
import eatyourbeets.cards.base.CardSeries;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Grandmaster_Phase1 extends AnimatorCard_UltraRare {
    public static final EYBCardData DATA = Register(Grandmaster_Phase1.class)
            .SetPower(0, CardRarity.SPECIAL)
            .SetColor(CardColor.COLORLESS)
            .SetSeries(CardSeries.LegendOfHeroesTrails)
            .PostInitialize(data ->
            {
                data.AddPreview(new Grandmaster_Phase2(), true);
                data.AddPreview(new Grandmaster_Phase3(), true);
                data.AddPreview(new Grandmaster_Phase4(), true);
            });

    public Grandmaster_Phase1() {
        super(DATA);

        Initialize(0, 0, 0);
        SetUpgrade(0, 0, 0);

        SetAffinity_Yellow(1);
    }

    @Override
    protected void OnUpgrade()
    {
        super.OnUpgrade();

        SetHaste(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.StackPower(new Grandmaster_Phase1Power(p, 1));
    }

    public static class Grandmaster_Phase1Power extends AnimatorPower {

        public Grandmaster_Phase1Power(AbstractCreature owner, int amount) {
            super(owner, Grandmaster_Phase1.DATA);
            Initialize(amount);
        }

        @Override
        public void atStartOfTurnPostDraw() {
            super.atStartOfTurnPostDraw();

            flash();

            GameActions.Bottom.FetchFromPile(name, amount, player.exhaustPile)
                    .ShowEffect(true, true)
                    .SetOptions(true, true);

            int numOrbsToRemove = 3;
            int totalLightning = GameUtilities.GetOrbCount(Lightning.ORB_ID);

            if (totalLightning >= numOrbsToRemove) {
                for (AbstractOrb orb : player.orbs) {
                    if (Lightning.ORB_ID.equals(orb.ID)) {
                        numOrbsToRemove--;
                        GameActions.Bottom.RemoveOrb(orb);

                        if (numOrbsToRemove <= 0) {
                            break;
                        }
                    }
                }

                if (numOrbsToRemove <= 0) {
                      GameActions.Top.MakeCardInHand(new Grandmaster_Phase2());
                      RemovePower();
                }
            }
        }
        @Override
        public void updateDescription() {
            description = FormatDescription(0, amount);
        }
    }
}