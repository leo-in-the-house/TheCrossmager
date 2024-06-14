package eatyourbeets.cards.animator.basic.pokemon;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.resources.GR;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Zorua extends PokemonCard {
    public static final EYBCardData DATA = Register(Zorua.class)
            .SetSkill(1, CardRarity.BASIC, EYBCardTarget.None);

    public Zorua() {
        super(DATA);

        Initialize(0, 0, 1);
        SetUpgrade(0, 0, 1);
        SetEvolution(new Zoroark());

        SetExhaust(true);

        SetAffinity_Black(1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.SelectFromHand(name, magicNumber, false)
                .SetOptions(true, true, true)
                .SetFilter(c -> c.rarity == CardRarity.BASIC || c.rarity == CardRarity.COMMON)
                .SetMessage(GR.Common.Strings.HandSelection.Copy)
                .AddCallback(cards ->
                {
                    for (AbstractCard c : cards)
                    {
                        GameActions.Top.MakeCardInHand(c.makeStatEquivalentCopy());
                    }
                });
    }
}