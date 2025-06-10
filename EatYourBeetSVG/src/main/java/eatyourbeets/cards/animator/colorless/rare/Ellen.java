package eatyourbeets.cards.animator.colorless.rare;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.curse.common.Curse_Regret;
import eatyourbeets.cards.animator.special.Ellen_Viola;
import eatyourbeets.cards.animator.special.Viola_Ellen;
import eatyourbeets.cards.base.*;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.effects.VFX;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.ui.common.EYBCardPopupActions;
import eatyourbeets.utilities.*;

public class Ellen extends AnimatorCard {
    public static final EYBCardData DATA = Register(Ellen.class)
            .SetAttack(2, CardRarity.RARE, EYBAttackType.Elemental, EYBCardTarget.ALL)
            .SetColor(CardColor.COLORLESS)
            .SetSeries(CardSeries.TheWitchsHouse)
            .PostInitialize(data ->
            {
                data.AddPopupAction(new EYBCardPopupActions.WitchsHouse_Ellen_BodySwitchRitual(Viola.DATA, Ellen.DATA, Viola_Ellen.DATA, Ellen_Viola.DATA, Curse_Regret.DATA));
                data.AddPreview(new Viola_Ellen(), false);
                data.AddPreview(new Ellen_Viola(), false);
                data.AddPreview(new Curse_Regret(), false);
            });

    public Ellen() {
        super(DATA);

        Initialize(8, 0, 0);
        SetUpgrade(4, 0, 0);

        SetAffinity_Violet(2);

        SetUnique(true, true);
        SetExhaust(true);
        SetHealing(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.SFX("ORB_DARK_EVOKE");
        GameActions.Bottom.VFX(VFX.Mindblast(p.dialogX, p.dialogY).SetColor(Color.VIOLET), 0.2f);

        GameActions.Bottom.DealDamageToAll(this, AttackEffects.DARK);

        if (CombatStats.TryActivateLimited(cardID)) {
            GameActions.Bottom.PurgeFromPile(name, 1, player.exhaustPile)
                .SetFilter(card -> !GameUtilities.IsHindrance(card))
                .SetOptions(true, true)
                .AddCallback(cards -> {
                    int numCardsPurged = cards.size();

                    if (numCardsPurged > 0) {
                        GameActions.Bottom.ModifyAllInstances(uuid, AbstractCard::upgrade)
                                .IncludeMasterDeck(true)
                                .IsCancellable(false);

                        final WeightedList<AbstractCard> possibleCards = GameUtilities.GetCardsInCombatWeighted(GenericCondition.FromT1(c -> GameUtilities.HasAffinity(c, Affinity.Yellow) && !GameUtilities.HasAffinity(c, Affinity.Star)));

                        CardGroup group = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);

                        for (int i=0; i<3; i++) {
                            group.addToBottom(possibleCards.Retrieve(rng, true));
                        }

                        GameActions.Bottom.SelectFromPile(name, 1, group)
                                .SetOptions(false, false)
                                .AddCallback(cardsToAdd ->
                                {
                                    for (AbstractCard card : cardsToAdd) {
                                        AbstractCard cardToAdd = card.makeCopy();

                                        if (upgraded) {
                                            cardToAdd.upgrade();
                                        }

                                        GameEffects.TopLevelQueue.ShowAndObtain(cardToAdd);
                                    }
                                });
                    }
                });
        }
    }
}