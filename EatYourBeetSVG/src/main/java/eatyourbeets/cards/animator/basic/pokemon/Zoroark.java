package eatyourbeets.cards.animator.basic.pokemon;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBAttackType;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.resources.GR;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Zoroark extends PokemonCard {
    public static final EYBCardData DATA = Register(Zoroark.class)
            .SetAttack(2, CardRarity.BASIC, EYBAttackType.Piercing, EYBCardTarget.Normal);

    public Zoroark() {
        super(DATA);

        Initialize(20, 0, 1);
        SetUpgrade(0, 0, 1);

        SetFading(true);

        SetAffinity_Black(1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.DealDamage(this, m, AttackEffects.CLAW);

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