package eatyourbeets.cards.animator.series.Katanagatari;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Biyorigo extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Biyorigo.class)
            .SetPower(2, CardRarity.RARE)
            .SetSeriesFromClassPackage();

    public Biyorigo()
    {
        super(DATA);

        Initialize(0, 0, 1, 0);
        SetUpgrade(0, 0, 1, 0);

        SetAffinity_Green(1);
        SetAffinity_Blue(1);

        SetEthereal(true);
    }

    @Override
    protected void OnUpgrade()
    {
        super.OnUpgrade();

        SetEthereal(false);
    }

    @Override
    public void triggerOnAffinitySeal(boolean reshuffle)
    {
        super.triggerOnAffinitySeal(reshuffle);

        GameActions.Bottom.GainArtifact(1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.GainBlock(block);
        GameActions.Bottom.StackPower(new BiyorigoPower(p, magicNumber));
    }

    public static class BiyorigoPower extends AnimatorPower
    {
        public BiyorigoPower(AbstractCreature owner, int amount)
        {
            super(owner, Biyorigo.DATA);

            Initialize(amount);
        }

        @Override
        public void atEndOfTurn(boolean isPlayer) {
            super.atEndOfTurn(isPlayer);

            int metallizeMultiplier = 0;

            for (AbstractCard card : player.hand.group) {
                if (GameUtilities.IsSealed(card)) {
                    metallizeMultiplier++;
                }
            }

            GameActions.Bottom.GainMetallicize(metallizeMultiplier * amount);
        }
    }
}