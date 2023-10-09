package eatyourbeets.cards.animator.series.GoblinSlayer;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.Lightning;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class NobleFencer extends AnimatorCard
{
    public static final EYBCardData DATA = Register(NobleFencer.class)
            .SetSkill(2, CardRarity.UNCOMMON, EYBCardTarget.None)
            
            .SetSeriesFromClassPackage();

    public NobleFencer()
    {
        super(DATA);

        Initialize(0, 0, 2);
        SetUpgrade(0, 0, 1);

        SetAffinity_Yellow(1);
        SetAffinity_Violet(1);
    }

    @Override
    protected void OnUpgrade()
    {
        SetRetain(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.ChannelOrbs(Lightning::new, magicNumber);

        GameActions.Bottom.StackPower(new NobleFencerPower(p, 1));
    }

    public class NobleFencerPower extends AnimatorPower
    {
        public NobleFencerPower(AbstractCreature owner, int amount)
        {
            super(owner, NobleFencer.DATA);

            Initialize(amount);
        }

        @Override
        public void atEndOfTurn(boolean isPlayer)
        {
            super.atEndOfTurn(isPlayer);

            int numHindrances = 0;

            for (AbstractCard card : player.hand.group) {
                if (GameUtilities.IsHindrance(card)) {
                    numHindrances++;

                    GameActions.Bottom.Exhaust(card);
                }
            }

            if (numHindrances > 0) {
                EvokeAllLightning(numHindrances);
            }
        }

        @Override
        public void atStartOfTurn()
        {
            super.atStartOfTurn();

            RemovePower();
        }

        private void EvokeAllLightning(int times) {
            for (AbstractOrb orb : player.orbs) {
                if (orb.ID.equals(Lightning.ORB_ID)) {
                    GameActions.Bottom.EvokeOrb(times, orb);
                }
            }
        }
    }
}