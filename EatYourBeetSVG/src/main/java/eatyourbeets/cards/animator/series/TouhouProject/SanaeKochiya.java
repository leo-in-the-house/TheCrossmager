package eatyourbeets.cards.animator.series.TouhouProject;

import com.megacrit.cardcrawl.cards.AbstractCard;
import eatyourbeets.utilities.GameUtilities;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.tokens.AffinityToken;
import eatyourbeets.cards.base.*;
import eatyourbeets.orbs.animator.Aether;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.utilities.GameActions;

public class SanaeKochiya extends AnimatorCard
{
    public static final EYBCardData DATA = Register(SanaeKochiya.class)
            .SetSkill(1, CardRarity.UNCOMMON, EYBCardTarget.None)
            .SetSeriesFromClassPackage()
            .PostInitialize(data -> data.AddPreviews(AffinityToken.GetCards(), false));

    public SanaeKochiya()
    {
        super(DATA);

        Initialize(0, 4, 1);
        SetUpgrade(0, 2, 1);

        SetAffinity_White(2, 0, 2);
        SetAffinity_Green(1);
    }

    @Override
    public void triggerOnAffinitySeal(boolean reshuffle)
    {
        super.triggerOnAffinitySeal(reshuffle);

        if (CombatStats.TryActivateLimited(cardID))
        {
            GameActions.Bottom.Add(AffinityToken.SelectTokenAction(name, false, false, 1))
            .AddCallback(cards ->
            {
                for (AbstractCard c : cards)
                {
                    GameActions.Bottom.MakeCardInHand(c);
                }
            });
        }
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.GainBlock(block);
        GameActions.Bottom.GainWhite(magicNumber, false);

        if (CheckSpecialCondition(false))
        {
            GameActions.Bottom.ChannelOrb(new Aether());
            exhaustOnUseOnce = true;
        }
    }

    @Override
    public boolean CheckSpecialCondition(boolean tryUse)
    {
        return GameUtilities.GetPowerAmount(Affinity.White) > 0;
    }
}