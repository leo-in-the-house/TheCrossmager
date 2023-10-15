package eatyourbeets.cards.animator.series.Katanagatari;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class ManiwaKyouken extends AnimatorCard
{
    public static final EYBCardData DATA = Register(ManiwaKyouken.class)
            .SetSkill(0, CardRarity.COMMON, EYBCardTarget.None)
            
            .SetSeries(CardSeries.Katanagatari);

    public ManiwaKyouken()
    {
        super(DATA);

        Initialize(0, 0, 0);
        SetUpgrade(0, 0, 0);

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

        GameActions.Bottom.Draw(1)
            .AddCallback(cards -> {
                for (AbstractCard card : cards) {
                    GameActions.Top.SealAffinities(card);
                }
            });
    }
}