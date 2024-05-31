package eatyourbeets.cards.animator.colorless.rare;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.special.Megunee_Zombie;
import eatyourbeets.cards.base.*;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.cards.base.attributes.TempHPAttribute;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Megunee extends AnimatorCard {
    public static final EYBCardData DATA = Register(Megunee.class)
            .SetSkill(1, CardRarity.RARE, EYBCardTarget.None)
            .SetColor(CardColor.COLORLESS)
            .SetSeries(CardSeries.GakkouGurashi)
            .PostInitialize(data -> data.AddPreview(new Megunee_Zombie(), false));

    public Megunee() {
        super(DATA);

        Initialize(0, 0, 8);
        SetUpgrade(0, 0, 3);

        SetAffinity_White(1);

        SetFading(true);
    }

    @Override
    public AbstractAttribute GetSpecialInfo()
    {
        return TempHPAttribute.Instance.SetCard(this, true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.GainTemporaryHP(magicNumber);
    }

    @Override
    public void triggerOnExhaust()
    {
        super.triggerOnExhaust();

        GameActions.Last.ReplaceCard(uuid, new Megunee_Zombie())
                .SetUpgrade(upgraded)
                .AddCallback(cards -> {
                    for (AbstractCard card : cards.values()) {
                        GameActions.Top.MoveCard(card, player.discardPile, player.drawPile);
                    }
                });

    }
}