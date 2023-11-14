package eatyourbeets.cards.animator.special;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.series.RozenMaiden.Suigintou;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.effects.SFX;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameEffects;
import eatyourbeets.utilities.GameUtilities;

import java.util.UUID;

public class Suigintou_BlackFeather extends AnimatorCard {
    public static final EYBCardData DATA = Register(Suigintou_BlackFeather.class)
            .SetSkill(0, CardRarity.SPECIAL, EYBCardTarget.None)
            .SetSeries(Suigintou.DATA.Series);

    private UUID linkedUUID;

    public Suigintou_BlackFeather() {
        super(DATA);

        Initialize(0, 0, 0);
        SetUpgrade(0, 0, 0);

        SetAffinity_Black(1);

        SetRetain(true);
        SetPurge(true);
    }

    public void setLinkedUUID(UUID linkedUUID) {
        this.linkedUUID = linkedUUID;
    }

    private boolean DrawSuigintou(CardGroup group)
    {
        for (AbstractCard c : group.group)
        {
            if (Suigintou.DATA.ID.equals(c.cardID))
            {
                if (group.type != CardGroup.CardGroupType.HAND)
                {
                    GameEffects.List.ShowCardBriefly(makeStatEquivalentCopy());
                    GameActions.Top.MoveCard(c, group, player.hand)
                            .ShowEffect(true, true)
                            .AddCallback(GameUtilities::Retain);
                }

                return true;
            }
        }

        return false;
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        if (!DrawSuigintou(player.exhaustPile)) {
            if (!DrawSuigintou(player.discardPile)) {
                DrawSuigintou(player.drawPile);
            }
        }

        if (linkedUUID != null && CombatStats.TryActivateLimited(cardID)) {
            GameActions.Bottom.ModifyAllInstances(linkedUUID, AbstractCard::upgrade)
                    .IncludeMasterDeck(true)
                    .IsCancellable(false);
            GameActions.Bottom.SFX(SFX.CARD_UPGRADE, 0.5f, 0.6f).SetDuration(0.25f, true);
        }
    }
}