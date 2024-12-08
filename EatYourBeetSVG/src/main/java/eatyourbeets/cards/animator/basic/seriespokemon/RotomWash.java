package eatyourbeets.cards.animator.basic.seriespokemon;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.basic.pokemon.PokemonCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.ui.common.EYBCardPopupActions;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class RotomWash extends PokemonCard {
    public static final EYBCardData DATA = Register(RotomWash.class)
            .SetSkill(1, CardRarity.BASIC, EYBCardTarget.None)
            .PostInitialize(data ->
            {
                data.AddPopupAction(new EYBCardPopupActions.Pokemon_Rotom());
                for (AbstractCard card : Rotom.GetForms().group) {
                    if (!card.cardID.equals(data.ID)) {
                        data.AddPreview(card.makeCopy(), false);
                    }
                }
            });

    public RotomWash() {
        super(DATA);

        Initialize(0, 0, 5);
        SetUpgrade(0, 0, 1);

        SetAffinity_Yellow(1);
        SetAffinity_Blue(1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(Rotom.rotomName);

        GameActions.Bottom.GainBlue(magicNumber);
        GameActions.Bottom.Draw(1);
    }
}