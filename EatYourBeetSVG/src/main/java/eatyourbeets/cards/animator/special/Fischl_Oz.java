package eatyourbeets.cards.animator.special;

import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.Dark;
import com.megacrit.cardcrawl.orbs.Lightning;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardSeries;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.RandomizedList;

public class Fischl_Oz extends AnimatorCard {
    public static final EYBCardData DATA = Register(Fischl_Oz.class)
            .SetPower(1, CardRarity.SPECIAL)
            .SetSeries(CardSeries.GenshinImpact);

    public Fischl_Oz() {
        super(DATA);

        Initialize(0, 0, 1);
        SetUpgrade(0, 0, 1);

        SetAffinity_Black(1);
    }

    @Override
    protected void SetUpgrade(int damage, int block) {
        super.SetUpgrade(damage, block);

        SetHaste(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.StackPower(new Fischl_OzPower(p, 1));
    }

    public static class Fischl_OzPower extends AnimatorPower {
        public Fischl_OzPower(AbstractCreature owner, int amount) {
            super(owner, Fischl_Oz.DATA);
            Initialize(amount);
        }


        public void atStartOfTurn()
        {
            super.atStartOfTurn();

            ResetAmount();
        }

        @Override
        public void onUseCard(AbstractCard card, UseCardAction action)
        {
            super.onUseCard(card, action);

            if (card.type == CardType.ATTACK && amount > 0)
            {
                RandomizedList<AbstractOrb> choices = new RandomizedList<>();
                choices.Add(new Lightning());
                choices.Add(new Dark());

                GameActions.Top.ChannelOrb(choices.Retrieve(rng));
                this.amount -= 1;
                updateDescription();
                flash();
            }
        }

        @Override
        public void updateDescription() {
            description = FormatDescription(0, amount);
        }
    }
}