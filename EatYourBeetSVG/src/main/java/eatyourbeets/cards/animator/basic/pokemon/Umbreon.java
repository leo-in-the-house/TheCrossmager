package eatyourbeets.cards.animator.basic.pokemon;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.effects.VFX;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Umbreon extends PokemonCard {
    public static final EYBCardData DATA = Register(Umbreon.class)
            .SetSkill(2, CardRarity.BASIC, EYBCardTarget.None);

    public Umbreon() {
        super(DATA);

        Initialize(0, 12, 5);
        SetUpgrade(0, 3, 0);

        SetAffinity_Violet(1, 0, 1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.GainBlock(block);

        if (CombatStats.TryActivateLimited(cardID)) {
            GameActions.Bottom.VFX(VFX.ShockWave(p.hb, Color.BLACK));
            GameActions.Bottom.Heal(magicNumber);
        }
    }
}