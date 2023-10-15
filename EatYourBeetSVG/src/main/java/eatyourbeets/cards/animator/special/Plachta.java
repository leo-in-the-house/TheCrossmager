package eatyourbeets.cards.animator.special;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.series.Atelier.SophieNeuenmuller;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardSeries;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.effects.SFX;
import eatyourbeets.interfaces.subscribers.OnCardCreatedSubscriber;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

import java.util.UUID;

public class Plachta extends AnimatorCard {
    public static final EYBCardData DATA = Register(Plachta.class)
            .SetPower(1, CardRarity.SPECIAL)
            .SetSeries(CardSeries.Atelier);
    static
    {
        DATA.AddPreview(new SophieNeuenmuller(), false);
    }

    private UUID linkedUUID;

    public Plachta() {
        super(DATA);

        Initialize(0, 0, 2);
        SetUpgrade(0, 0, 1);

        SetUnique(true, true);

        SetAffinity_Blue(1);
    }

    public void setLinkedUUID(UUID linkedUUID) {
        this.linkedUUID = linkedUUID;
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        if (linkedUUID != null) {
            GameActions.Bottom.ModifyAllInstances(linkedUUID, AbstractCard::upgrade)
                    .IncludeMasterDeck(true)
                    .IsCancellable(false);
            GameActions.Bottom.SFX(SFX.CARD_UPGRADE, 0.5f, 0.6f).SetDuration(0.25f, true);
        }

        GameActions.Bottom.StackPower(new PlachtaPower(p, magicNumber));
    }

    public static class PlachtaPower extends AnimatorPower implements OnCardCreatedSubscriber {
        public PlachtaPower(AbstractCreature owner, int amount) {
            super(owner, Plachta.DATA);
            Initialize(amount);
        }

        @Override
        public void updateDescription()
        {
            description = FormatDescription(0, amount);
        }

        @Override
        public void onInitialApplication()
        {
            super.onInitialApplication();

            CombatStats.onCardCreated.Subscribe(this);
        }

        @Override
        public void onRemove()
        {
            super.onRemove();

            CombatStats.onCardCreated.Unsubscribe(this);
        }

        @Override
        public void OnCardCreated(AbstractCard card, boolean startOfBattle) {
            if (enabled && !startOfBattle) {
                GameActions.Bottom.GainDuplication(1);

                reducePower(1);
                flashWithoutSound();

                if (amount <= 0) {
                    SetEnabled(false);
                    RemovePower();
                    flash();
                }
            }
        }
    }
}