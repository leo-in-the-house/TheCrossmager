package eatyourbeets.cards.animator.series.GoblinSlayer;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.cards.base.attributes.HPAttribute;
import eatyourbeets.resources.GR;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.ListSelection;

public class SwordMaiden extends AnimatorCard
{
    public static final EYBCardData DATA = Register(SwordMaiden.class)
            .SetSkill(2, CardRarity.RARE, EYBCardTarget.None)
            .SetSeriesFromClassPackage();

    public SwordMaiden()
    {
        super(DATA);

        Initialize(0, 0, 9, 0);
        SetUpgrade(0, 0, 4, 0);

        SetAffinity_White(2);
        SetAffinity_Pink(1);

        SetExhaust(true);
        SetHealing(true);
        SetObtainableInCombat(false);

        SetAffinityRequirement(Affinity.White, 3);
        SetAffinityRequirement(Affinity.Pink, 3);

    }

    @Override
    protected void OnUpgrade()
    {
        SetRetain(true);
    }

    @Override
    public AbstractAttribute GetSecondaryInfo()
    {
        return HPAttribute.Instance.SetCard(this, true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.Heal(magicNumber);
        GameActions.Bottom.RemoveDebuffs(player, ListSelection.Last(0), 1);

        if (CheckSpecialCondition(false)) {
            GameActions.Bottom.SelectFromPile(name, player.drawPile.size(), player.drawPile)
                    .SetMessage(GR.Common.Strings.GridSelection.Discard)
                    .SetFilter(GameUtilities::IsHindrance)
                    .SetOptions(false, true)
                    .AddCallback(cards -> {
                        for (AbstractCard card : cards) {
                            GameActions.Top.Discard(card, player.discardPile);
                            GameActions.Top.SealAffinities(card);
                        }
                    });
        }
    }
}