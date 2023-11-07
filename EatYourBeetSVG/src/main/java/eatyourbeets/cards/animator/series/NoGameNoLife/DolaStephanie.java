package eatyourbeets.cards.animator.series.NoGameNoLife;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.actions.special.RefreshHandLayout;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.resources.GR;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class DolaStephanie extends AnimatorCard
{
    public static final EYBCardData DATA = Register(DolaStephanie.class)
        .SetSkill(0, CardRarity.COMMON, EYBCardTarget.None)
        .SetSeriesFromClassPackage();

    public DolaStephanie()
    {
        super(DATA);

        Initialize(0, 0, 1);
        SetUpgrade(0, 0, 1);

        SetAffinity_Yellow(1);

        SetHaste(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.Draw(magicNumber);
        GameActions.Bottom.SelectFromHand(name, 1, false)
        .SetMessage(GR.Common.Strings.HandSelection.MoveToDrawPile)
        .AddCallback(info, (info2, cards) ->
        {
            for (AbstractCard c : cards)
            {
                GameActions.Top.MoveCard(c, player.hand, player.drawPile);
            }

            GameActions.Bottom.Add(new RefreshHandLayout());
        });
    }
}