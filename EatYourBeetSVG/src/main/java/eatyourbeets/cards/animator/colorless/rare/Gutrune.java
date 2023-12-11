package eatyourbeets.cards.animator.colorless.rare;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.special.DeepPrincess;
import eatyourbeets.cards.base.*;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.cards.base.attributes.HPAttribute;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Gutrune extends AnimatorCard {
    public static final EYBCardData DATA = Register(Gutrune.class)
            .SetSkill(1, CardRarity.RARE, EYBCardTarget.None)
            .SetSeries(CardSeries.EtrianOdyssey)
            .SetColor(CardColor.COLORLESS)
            .PostInitialize(data -> data.AddPreview(new DeepPrincess(), false));

    public Gutrune() {
        super(DATA);

        Initialize(0, 4, 4);
        SetUpgrade(0, 2, 2);

        SetAffinity_Blue(2, 0, 1);
        SetAffinity_Black(2, 0, 1);

        SetFading(true);
    }

    @Override
    public void triggerOnExhaust()
    {
        GameActions.Bottom.MakeCardInDiscardPile(new DeepPrincess());
    }

    @Override
    public AbstractAttribute GetSpecialInfo()
    {
        return HPAttribute.Instance.SetCard(this, true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.GainBlock(block);
        GameActions.Bottom.Heal(magicNumber);
    }
}