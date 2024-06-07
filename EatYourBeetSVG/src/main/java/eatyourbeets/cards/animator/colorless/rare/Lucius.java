package eatyourbeets.cards.animator.colorless.rare;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.special.Lugia;
import eatyourbeets.cards.base.*;
import eatyourbeets.resources.GR;
import eatyourbeets.ui.common.EYBCardPopupActions;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameEffects;
import eatyourbeets.utilities.GameUtilities;

public class Lucius extends AnimatorCard {
    public static final EYBCardData DATA = Register(Lucius.class)
            .SetSkill(3, CardRarity.RARE, EYBCardTarget.None)
            .SetColor(CardColor.COLORLESS)
            .SetSeries(CardSeries.PokemonReminiscencia)
            .PostInitialize(data ->
            {
                data.AddPopupAction(new EYBCardPopupActions.Pokemon_Lucius(Lugia.DATA, 3));
                data.AddPreview(new Lugia(), true);
            });

    public Lucius() {
        super(DATA);

        Initialize(0, 0, 0);
        SetUpgrade(0, 0, 0);
        SetCostUpgrade(-1);

        SetAffinity_Blue(2);

        SetRetain(true);
        SetPurge(true);
    }

    @Override
    public boolean cardPlayable(AbstractMonster m)
    {
        if (super.cardPlayable(m))
        {
            return player.exhaustPile.group.stream().filter(card -> card.rarity == CardRarity.SPECIAL || card.color == CardColor.COLORLESS).count() >= 5;
        }

        return false;
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.FetchFromPile(name, 1, p.exhaustPile)
            .SetOptions(false, true)
            .SetFilter(card -> card.rarity == CardRarity.SPECIAL || card.color == CardColor.COLORLESS)
            .SetMessage(GR.Common.Strings.GridSelection.PermanentlyAdd)
            .AddCallback(cards -> {
                if (cards.size() > 0) {
                    AbstractCard chosenCard = cards.get(0);

                    GameEffects.TopLevelQueue.ShowAndObtain(chosenCard);
                }
            });
    }
}