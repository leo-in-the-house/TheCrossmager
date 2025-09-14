package eatyourbeets.cards.animator.ultrarare;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.watcher.SkipEnemiesTurnAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.Lightning;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.TimeWarpTurnEndEffect;
import eatyourbeets.cards.base.*;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.resources.GR;
import eatyourbeets.utilities.ColoredString;
import eatyourbeets.utilities.Colors;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Grandmaster_Phase3 extends AnimatorCard_UltraRare {
    public static final EYBCardData DATA = Register(Grandmaster_Phase3.class)
            .SetPower(0, CardRarity.SPECIAL)
            .SetColor(CardColor.COLORLESS)
            .SetSeries(CardSeries.LegendOfHeroesTrails)
            .PostInitialize(data ->
            {
                data.AddPreview(new Grandmaster_Phase4(), true);
            });

    public Grandmaster_Phase3() {
        super(DATA);

        Initialize(0, 0, 0);
        SetUpgrade(0, 0, 0);

        SetAffinity_Teal(1);
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

        GameActions.Bottom.StackPower(new Grandmaster_Phase3Power(p, 1));
    }

    public static class Grandmaster_Phase3Power extends AnimatorPower {

        protected int turnCount = 1;
        public Grandmaster_Phase3Power(AbstractCreature owner, int amount) {
            super(owner, Grandmaster_Phase3.DATA);
            Initialize(amount);
        }

        @Override
        public void atStartOfTurnPostDraw() {
            super.atStartOfTurnPostDraw();

            if (turnCount <= 0) {
                flash();
                turnCount = 1;
                GameActions.Bottom.VFX(new TimeWarpTurnEndEffect());
                GameActions.Bottom.VFX(new BorderFlashEffect(Color.WHITE, true));
                GameActions.Bottom.Add(new SkipEnemiesTurnAction());

                int numOrbsToRemove = 5;
                int totalLightning = GameUtilities.GetOrbCount(Lightning.ORB_ID);

                AbstractCard unique = FindUniqueCardInDiscard();

                if (totalLightning >= numOrbsToRemove && unique != null) {
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
                        GameActions.Bottom.Exhaust(unique)
                            .AddCallback(() -> {
                                GameActions.Top.MakeCardInHand(new Grandmaster_Phase4());
                            });
                        RemovePower();
                    }
                }
            }
            else {
                turnCount--;
            }
        }

        private AbstractCard FindUniqueCardInDiscard() {
            for (AbstractCard card : player.discardPile.group) {
                if (card.hasTag(GR.Enums.CardTags.UNIQUE)) {
                    return card;
                }
            }

            return null;
        }

        @Override
        protected ColoredString GetSecondaryAmount(Color c)
        {
            return new ColoredString(turnCount, Colors.Lerp(Color.LIGHT_GRAY, Settings.PURPLE_COLOR, turnCount, c.a));
        }

        @Override
        public void updateDescription() {
            description = FormatDescription(0, amount);
        }
    }
}