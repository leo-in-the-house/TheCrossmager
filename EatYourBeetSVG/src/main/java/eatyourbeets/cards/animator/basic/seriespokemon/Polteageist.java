package eatyourbeets.cards.animator.basic.seriespokemon;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.basic.pokemon.PokemonCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.resources.GR;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Polteageist extends PokemonCard {
    public static final EYBCardData DATA = Register(Polteageist.class)
            .SetSkill(2, CardRarity.BASIC, EYBCardTarget.None);

    public Polteageist() {
        super(DATA);

        Initialize(0, 0, 2);
        SetUpgrade(0, 0, 1);

        SetAffinity_Black(1);
        SetAffinity_Blue(1);
    }

    @Override
    protected void OnUpgrade()
    {
        SetHaste(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.SelectFromHand(name, 1, false)
        .SetFilter(card -> (card.rarity == CardRarity.COMMON || card.rarity == CardRarity.BASIC))
        .SetOptions(true, true, true)
        .SetMessage(GR.Common.Strings.HandSelection.Copy)
        .AddCallback(cards ->
        {
            for (AbstractCard c : cards)
            {
                for (int i=0; i<magicNumber; i++) {
                    GameActions.Top.MakeCardInHand(GameUtilities.Imitate(c));
                }
            }
        });
    }
}