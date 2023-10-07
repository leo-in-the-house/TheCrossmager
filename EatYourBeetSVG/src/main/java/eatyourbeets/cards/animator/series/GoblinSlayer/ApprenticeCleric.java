package eatyourbeets.cards.animator.series.GoblinSlayer;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.cards.base.attributes.TempHPAttribute;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

import java.util.LinkedList;
import java.util.List;

public class ApprenticeCleric extends AnimatorCard
{
    public static final EYBCardData DATA = Register(ApprenticeCleric.class)
            .SetSkill(0, CardRarity.COMMON, EYBCardTarget.None)
            .SetSeriesFromClassPackage();

    public ApprenticeCleric()
    {
        super(DATA);

        Initialize(0, 3, 2);
        SetUpgrade(0, 2, 1);

        SetAffinity_White(1, 0, 0);
    }

    @Override
    public AbstractAttribute GetSpecialInfo()
    {
        return (magicNumber > 0) ? TempHPAttribute.Instance.SetCard(this, true) : null;
    }

    @Override
    protected void Refresh(AbstractMonster enemy)
    {
        super.Refresh(enemy);

        SetUnplayable(getHindrancesInDrawPile().size() <= 0);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.ExhaustFromPile(name, 1, player.drawPile)
            .SetFilter(GameUtilities::IsHindrance)
            .SetOptions(true, false)
            .AddCallback(cards -> {
                if (cards.size() > 0) {
                    for (AbstractCard c : cards) {
                        GameActions.Top.SealAffinities(c, false);
                    }

                    GameActions.Top.GainBlock(block);
                    GameActions.Top.GainTemporaryHP(magicNumber);
                }
            });
    }

    private List<AbstractCard> getHindrancesInDrawPile() {
        List<AbstractCard> drawPile = player.drawPile.group;
        List<AbstractCard> hindrances = new LinkedList<>();

        for (AbstractCard card : drawPile) {
            if (GameUtilities.IsHindrance(card)) {
                hindrances.add(card);
            }
        }

        return hindrances;
    }
}