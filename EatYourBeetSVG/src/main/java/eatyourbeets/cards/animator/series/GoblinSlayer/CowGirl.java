package eatyourbeets.cards.animator.series.GoblinSlayer;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.actions.animator.CreateRandomGoblins;
import eatyourbeets.cards.animator.status.GoblinChampion;
import eatyourbeets.cards.animator.status.GoblinKing;
import eatyourbeets.cards.animator.status.GoblinShaman;
import eatyourbeets.cards.animator.status.GoblinSoldier;
import eatyourbeets.cards.base.*;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class CowGirl extends AnimatorCard
{
    public static final EYBCardData DATA = Register(CowGirl.class)
            .SetSkill(0, CardRarity.UNCOMMON, EYBCardTarget.None)
            .SetSeriesFromClassPackage()
            .PostInitialize(data ->
            {
                data.AddPreview(new GoblinSoldier(), false);
                data.AddPreview(new GoblinShaman(), false);
                data.AddPreview(new GoblinChampion(), false);
                data.AddPreview(new GoblinKing(), false);
            });

    public CowGirl()
    {
        super(DATA);

        Initialize(0, 0, 2);
        SetUpgrade(0, 0, 0);

        SetAffinity_Brown(1);
    }

    @Override
    protected void OnUpgrade()
    {
        SetRetainOnce(true);
    }

    @Override
    public void triggerWhenDrawn() {
        super.triggerWhenDrawn();

        if (rng.randomBoolean())
        {
            GameActions.Top.MakeCardInDiscardPile(CreateRandomGoblins.GetRandomGoblin(rng));
        }
        else {
            GameActions.Top.MakeCardInDrawPile(CreateRandomGoblins.GetRandomGoblin(rng));
        }
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.Draw(magicNumber)
            .AddCallback(cards -> {
                for (AbstractCard card : cards) {
                    if (card instanceof AnimatorCard) {
                        AnimatorCard animatorCard = (AnimatorCard) card;
                        for (EYBCardAffinity affinity : animatorCard.affinities.List) {
                            if (affinity.level == 1) {
                                GameUtilities.AddAffinityToCard(card, affinity.type, 1);
                            }
                        }
                    }
                }
            });
    }
}