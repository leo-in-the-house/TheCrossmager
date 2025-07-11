package eatyourbeets.cards.animator.basic.seriespokemon;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.basic.pokemon.PokemonCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.stances.TranceStance;
import eatyourbeets.ui.common.EYBCardPopupActions;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class RotomFan extends PokemonCard {
    public static final EYBCardData DATA = Register(RotomFan.class)
            .SetSkill(2, CardRarity.BASIC, EYBCardTarget.None)
            .PostInitialize(data ->
            {
                data.AddPopupAction(new EYBCardPopupActions.Pokemon_Rotom());
                for (AbstractCard card : Rotom.GetForms().group) {
                    if (!card.cardID.equals(data.ID)) {
                        data.AddPreview(card.makeCopy(), false);
                    }
                }
            });

    public RotomFan() {
        super(DATA);

        Initialize(0, 0, 6);
        SetUpgrade(0, 0, 1);

        SetAffinity_Yellow(1);
        SetAffinity_Green(1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(Rotom.rotomName);

        GameActions.Bottom.GainGreen(magicNumber);
        GameActions.Bottom.ChangeStance(TranceStance.STANCE_ID);
    }
}