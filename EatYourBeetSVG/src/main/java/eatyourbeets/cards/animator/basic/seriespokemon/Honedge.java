package eatyourbeets.cards.animator.basic.seriespokemon;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.basic.pokemon.PokemonCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBAttackType;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Honedge extends PokemonCard {
    public static final EYBCardData DATA = Register(Honedge.class)
            .SetAttack(1, CardRarity.BASIC, EYBAttackType.Normal, EYBCardTarget.Normal);

    public Honedge() {
        super(DATA);

        Initialize(5, 5, 0);
        SetUpgrade(3, 3, 0);

        SetAffinity_Black(1);
        SetAffinity_Teal(1);

        SetEvolution(new Doublade());
    }

    @Override
    public AbstractAttribute GetDamageInfo()
    {
        if (!GameUtilities.IsSealed(this))
        {
            return super.GetDamageInfo();
        }

        return null;
    }

    @Override
    public AbstractAttribute GetBlockInfo()
    {
        if (GameUtilities.IsSealed(this))
        {
            return super.GetBlockInfo();
        }

        return null;
    }

    @Override
    public void triggerOnAffinitySeal(boolean reshuffle)
    {
        super.triggerOnAffinitySeal(reshuffle);

        this.cardText.OverrideDescription(cardData.Strings.EXTENDED_DESCRIPTION[0], true);
        this.type = CardType.SKILL;
        this.target = CardTarget.NONE;
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        if (GameUtilities.IsSealed(this)) {
            GameActions.Bottom.GainBlock(block);
        }
        else {
            GameActions.Bottom.DealDamage(this, m, AttackEffects.SLASH_DIAGONAL);
        }
    }
}