package eatyourbeets.cards.animator.series.GoblinSlayer;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.actions.animator.CreateRandomGoblins;
import eatyourbeets.cards.animator.status.*;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.cards.base.attributes.TempHPAttribute;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Priestess extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Priestess.class)
            .SetSkill(1, CardRarity.UNCOMMON, EYBCardTarget.ALL)
            .SetSeriesFromClassPackage()
            .PostInitialize(data ->
            {
                data.AddPreview(new Status_Dazed(), false);
                data.AddPreview(new GoblinSoldier(), false);
                data.AddPreview(new GoblinShaman(), false);
                data.AddPreview(new GoblinChampion(), false);
                data.AddPreview(new GoblinKing(), false);
            });


    public Priestess()
    {
        super(DATA);

        Initialize(0, 0, 7, 3);
        SetUpgrade(0, 0, 4);

        SetAffinity_White(1);

        SetFading(true);
    }


    @Override
    public void triggerWhenDrawn() {
        super.triggerWhenDrawn();

        if (CombatStats.TryActivateLimited(cardID)) {
            for (int i=0; i<secondaryValue; i++) {
                GameActions.Top.MakeCardInHand(CreateRandomGoblins.GetRandomGoblin(rng));
            }
        }
    }

    @Override
    public AbstractAttribute GetSpecialInfo()
    {
        return TempHPAttribute.Instance.SetCard(this, true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.GainTemporaryHP(magicNumber);

        for (AbstractCard card : player.hand.group) {
            if (GameUtilities.IsHindrance(card)) {
                GameActions.Bottom.ReplaceCard(card.uuid, new Status_Dazed());
            }
        }
    }
}