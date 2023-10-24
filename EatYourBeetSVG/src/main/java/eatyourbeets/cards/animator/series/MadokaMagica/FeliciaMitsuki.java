package eatyourbeets.cards.animator.series.MadokaMagica;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class FeliciaMitsuki extends AnimatorCard
{
    public static final EYBCardData DATA = Register(FeliciaMitsuki.class)
            .SetAttack(1, CardRarity.COMMON)
            .SetSeriesFromClassPackage();

    public FeliciaMitsuki()
    {
        super(DATA);

        Initialize(8, 0, 2);
        SetUpgrade(3, 0, 1);

        SetAffinity_Pink(1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.DealDamage(this, m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        GameActions.Bottom.ExhaustFromPile(name, magicNumber, player.drawPile, player.discardPile, player.hand)
                .SetOptions(true, true)
                .SetFilter(card -> card.type == CardType.CURSE);
    }
}