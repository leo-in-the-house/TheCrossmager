package eatyourbeets.cards.animator.colorless.rare;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class AlisaIlinichinaAmiella extends AnimatorCard {
    public static final EYBCardData DATA = Register(AlisaIlinichinaAmiella.class)
            .SetAttack(3, CardRarity.RARE, EYBAttackType.Ranged, EYBCardTarget.Random)
            .SetColor(CardColor.COLORLESS)
            .SetSeries(CardSeries.GodEater);

    public AlisaIlinichinaAmiella() {
        super(DATA);

        Initialize(4, 0, 6);
        SetUpgrade(0, 0, 3);

        SetAffinity_Red(2, 0, 0);
        SetAffinity_Violet(1, 0, 0);
    }

    @Override
    public void Refresh(AbstractMonster enemy)
    {
        super.Refresh(enemy);

        int numRedScale = player.exhaustPile.size();
        int numVioletScale = player.hand.size() / 2;

        if (numRedScale > 0) {
            SetScaling(Affinity.Red, numRedScale);
        }

        if (numVioletScale > 0) {
            SetScaling(Affinity.Violet, numVioletScale);
        }
    }

    @Override
    public AbstractAttribute GetDamageInfo()
    {
        return super.GetDamageInfo().AddMultiplier(magicNumber);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        for (int i=0; i<magicNumber; i++) {
            GameActions.Bottom.DealDamageToRandomEnemy(this, AttackEffects.GUNSHOT)
                .SetVFXColor(Color.RED)
                .SetOptions(true, false)
                .SetDuration(0.025f, false);
        }
    }
}