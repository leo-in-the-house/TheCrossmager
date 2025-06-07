package eatyourbeets.cards.animator.basic.seriespokemon;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.basic.pokemon.*;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Kirlia extends PokemonCard {
    public static final EYBCardData DATA = Register(Kirlia.class)
            .SetSkill(1, CardRarity.BASIC, EYBCardTarget.None);
    static
    {
        DATA.AddPreview(new Gardevoir(), false);
        DATA.AddPreview(new Gallade(), false);
    }

    public Kirlia() {
        super(DATA);

        Initialize(0, 7, 1);
        SetUpgrade(0, 3, 0);

        SetAffinity_Pink(1);
        SetAffinity_White(1);

        HasSpecialEvolution();
    }

    @Override
    public void Evolve() {
        int numAttacks = (int)player.masterDeck.group.stream().filter(card -> card.baseDamage > 0).count();
        int numBlocks = (int)player.masterDeck.group.stream().filter(card -> card.baseBlock > 0).count();

        if (numAttacks > numBlocks) {
            EvolveInto(new Gallade());
        }
        else {
            EvolveInto(new Gardevoir());
        }
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.GainBlock(block);
        GameActions.Bottom.GainBlur(magicNumber);
    }
}