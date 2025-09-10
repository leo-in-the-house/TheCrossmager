package eatyourbeets.cards.animator.colorless.uncommon;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.series.BlueArchive.AzusaShirasu;
import eatyourbeets.cards.animator.special.SaoriJoumae_Atsuko;
import eatyourbeets.cards.animator.special.SaoriJoumae_Hiyori;
import eatyourbeets.cards.animator.special.SaoriJoumae_Misaki;
import eatyourbeets.cards.base.*;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.utilities.CardSelection;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class SaoriJoumae extends AnimatorCard {
    public static final EYBCardData DATA = Register(SaoriJoumae.class)
            .SetColor(CardColor.COLORLESS)
            .SetAttack(2, CardRarity.UNCOMMON, EYBAttackType.Normal, EYBCardTarget.Normal)
            .SetSeries(CardSeries.BlueArchive)
            .PostInitialize(data ->
            {
                data.AddPreview(new SaoriJoumae_Atsuko(), true);
                data.AddPreview(new SaoriJoumae_Misaki(), true);
                data.AddPreview(new SaoriJoumae_Hiyori(), true);
                data.AddPreview(new AzusaShirasu(), true);
            });

    public SaoriJoumae() {
        super(DATA);

        Initialize(10, 0, 0);
        SetUpgrade(4, 0, 0);

        SetAffinity_Green(1, 0, 1);
        SetAffinity_Violet(1);

        SetExhaust(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.DealDamage(this, m, AttackEffects.GUNSHOT);

        CardGroup ariusCards = GetAllAriusSquadCards();

        for (AbstractCard card : ariusCards.group) {
            GameActions.Bottom.MakeCardInDrawPile(card.makeCopy())
                    .SetDestination(CardSelection.Top)
                    .SetUpgrade(upgraded, true);
        }
    }

    private CardGroup GetAllAriusSquadCards() {

        CardGroup group = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);

        group.addToBottom(new SaoriJoumae_Atsuko());
        group.addToBottom(new SaoriJoumae_Misaki());
        group.addToBottom(new SaoriJoumae_Hiyori());
        group.addToBottom(new AzusaShirasu());

        return group;
    }
}