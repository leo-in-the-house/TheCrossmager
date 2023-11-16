package eatyourbeets.cards.animator.ultrarare;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;
import eatyourbeets.cards.base.*;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.RandomizedList;

public class Veldora extends AnimatorCard_UltraRare
{
    public static final EYBCardData DATA = Register(Veldora.class)
            .SetSkill(4, CardRarity.SPECIAL, EYBCardTarget.None)
            .SetColor(CardColor.COLORLESS)
            .SetSeries(CardSeries.TenseiSlime);

    public Veldora()
    {
        super(DATA);

        Initialize(0, 10, 2);
        SetCostUpgrade(-1);

        SetAffinity_Star(1, 0, 1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.ChannelOrb(AbstractOrb.getRandomOrb(true));

        GameActions.Bottom.StackPower(new VeldoraPower(player, 1));
    }

    public static class VeldoraPower extends AnimatorPower {

        public VeldoraPower(AbstractCreature owner, int amount) {
            super(owner, Veldora.DATA);
            Initialize(amount);
        }

        @Override
        public void updateDescription() {
            description = FormatDescription(0, amount);
        }

        @Override
        public void atEndOfTurn(boolean isPlayer) {
            RandomizedList<AbstractOrb> orbs = new RandomizedList<>();

            for (AbstractOrb orb : player.orbs) {
                if (orb != null && !(orb instanceof EmptyOrbSlot)) {
                    orbs.Add(orb);
                }
            }

            for (int i=0; i<GameUtilities.GetTotalCostOfCardsInHand(); i++) {
                GameActions.Bottom.Callback(() -> {
                    AbstractOrb orb = orbs.Retrieve(rng, false);

                    for (int j=0; j<amount; j++) {
                        orb.onEvoke();
                    }
                });
            }

            RemovePower();
        }
    }
}