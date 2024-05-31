package eatyourbeets.cards.animator.colorless.rare;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.PetalEffect;
import com.megacrit.cardcrawl.vfx.RainbowCardEffect;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardSeries;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameEffects;
import eatyourbeets.utilities.GameUtilities;

import java.util.LinkedList;
import java.util.List;

public class HatsuneMiku extends AnimatorCard {
    public static final EYBCardData DATA = Register(HatsuneMiku.class)
            .SetPower(0, CardRarity.RARE)
            .SetSeries(CardSeries.Vocaloid)
            .SetColor(CardColor.COLORLESS);

    public HatsuneMiku() {
        super(DATA);

        Initialize(0, 0, 0);
        SetUpgrade(0, 0, 0);

        SetAffinity_Teal(1);
        SetEthereal(true);
    }

    @Override
    protected void OnUpgrade()
    {
        SetInnate(true);
    }


    @Override
    public boolean cardPlayable(AbstractMonster m)
    {
        if (super.cardPlayable(m))
        {
            return player.masterDeck.size() == 39;
        }

        return false;
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);
        GameEffects.Queue.BorderFlash(Color.SKY);
        GameEffects.Queue.Add(new PetalEffect());
        GameEffects.Queue.Add(new RainbowCardEffect());

        List<AbstractCard> upgradableCards = new LinkedList<>();
        upgradableCards.addAll(player.drawPile.group);
        upgradableCards.addAll(player.hand.group);
        upgradableCards.addAll(player.discardPile.group);

        for (AbstractCard card : upgradableCards) {
            if (card.canUpgrade()) {
                card.upgrade();
                card.applyPowers();
            }
        }

        GameActions.Bottom.StackPower(new HatsuneMikuPower(p, 1));
    }

    public static class HatsuneMikuPower extends AnimatorPower {
        public HatsuneMikuPower(AbstractCreature owner, int amount) {
            super(owner, HatsuneMiku.DATA);
            Initialize(amount);
        }

        @Override
        public void updateDescription() {
            description = FormatDescription(0, amount);
        }

        @Override
        public void onCardDraw(AbstractCard c)
        {
            c.setCostForTurn(-9);
        }
    }
}