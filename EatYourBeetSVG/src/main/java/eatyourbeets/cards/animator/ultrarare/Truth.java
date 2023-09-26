package eatyourbeets.cards.animator.ultrarare;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import eatyourbeets.cards.animator.status.Crystallize;
import eatyourbeets.cards.base.AnimatorCard_UltraRare;
import eatyourbeets.cards.base.CardSeries;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.interfaces.subscribers.OnChannelOrbSubscriber;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Truth extends AnimatorCard_UltraRare
{
    private static final Crystallize status = new Crystallize();

    public static final EYBCardData DATA = Register(Truth.class)
            .SetPower(0, CardRarity.SPECIAL)
            .SetColor(CardColor.COLORLESS)
            .SetSeries(CardSeries.FullmetalAlchemist)
            .PostInitialize(data -> data.AddPreview(status, false));

    public Truth()
    {
        super(DATA);

        Initialize(0, 0, 5);
        SetUpgrade(0, 0, 4);

        SetAffinity_Star(1);

        SetExhaust(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.GainRed(1);
        GameActions.Bottom.GainGreen(1);
        GameActions.Bottom.GainBlue(1);
        GameActions.Bottom.GainEnergy(magicNumber);

        GameActions.Bottom.StackPower(new Truth.TruthPower(p, magicNumber));
    }

    public static class TruthPower extends AnimatorPower implements OnChannelOrbSubscriber {
        public TruthPower(AbstractCreature owner, int amount) {
            super(owner, Truth.DATA);
            this.amount = amount;
            updateDescription();
        }


        @Override
        public void onInitialApplication()
        {
            super.onInitialApplication();

            CombatStats.onChannelOrb.Subscribe(this);
        }

        @Override
        public void onRemove()
        {
            super.onRemove();

            CombatStats.onChannelOrb.Unsubscribe(this);
        }


        @Override
        public void OnChannelOrb(AbstractOrb orb) {
            GameActions.Top.EvokeOrb(amount, orb);
        }
    }
}