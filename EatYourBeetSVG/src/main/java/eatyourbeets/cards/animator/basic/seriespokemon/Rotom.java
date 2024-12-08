package eatyourbeets.cards.animator.basic.seriespokemon;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.basic.pokemon.PokemonCard;
import eatyourbeets.cards.base.*;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.ui.common.EYBCardPopupActions;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Rotom extends PokemonCard {
    public static final EYBCardData DATA = Register(Rotom.class)
            .SetAttack(0, CardRarity.BASIC, EYBAttackType.Elemental, EYBCardTarget.ALL)
            .PostInitialize(data ->
            {
                data.AddPopupAction(new EYBCardPopupActions.Pokemon_Rotom());
                for (AbstractCard card : Rotom.GetForms().group) {
                    if (!card.cardID.equals(data.ID)) {
                        data.AddPreview(card.makeCopy(), false);
                    }
                }
            });

    public static String rotomName = "Rotom";


    public Rotom() {
        super(DATA);

        Initialize(1, 0, 0);
        SetUpgrade(2, 0, 0);

        SetAffinity_Yellow(1);
        SetAffinity_Black(1);

        SetScaling(Affinity.Red, 1);
        SetScaling(Affinity.Green, 1);
        SetScaling(Affinity.Blue, 1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.DealDamageToAll(this, AttackEffects.LIGHTNING);
    }

    public static CardGroup GetForms() {
        final CardGroup group = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);

        group.addToBottom(new Rotom());
        group.addToBottom(new RotomFan());
        group.addToBottom(new RotomFrost());
        group.addToBottom(new RotomHeat());
        group.addToBottom(new RotomMow());
        group.addToBottom(new RotomWash());

        return group;
    }
}