package eatyourbeets.cards.animator.colorless.uncommon;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.utilities.GameActions;

public class Hibiki extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Hibiki.class)
            .SetAttack(1, CardRarity.UNCOMMON, EYBAttackType.Ranged, EYBCardTarget.Random)
            .SetColor(CardColor.COLORLESS)
            .SetSeries(CardSeries.Kancolle);

    public Hibiki()
    {
        super(DATA);

        Initialize(2, 0, 3, 1);
        SetUpgrade(0, 0, 2, 0);

        SetAffinity_Brown(1, 0, 1);
    }

    @Override
    protected void OnUpgrade()
    {
        upgradedDamage = true;
    }

    @Override
    public AbstractAttribute GetDamageInfo()
    {
        return super.GetDamageInfo().AddMultiplier(magicNumber);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        for (int i = 0; i < magicNumber; i++)
        {
            GameActions.Bottom.DealDamageToRandomEnemy(this, AttackEffects.GUNSHOT)
            .SetSoundPitch(3.3f, 3.6f)
            .SetVFXColor(Color.LIGHT_GRAY)
            .SetOptions(true, false)
            .SetDuration(0.025f, false);
        }

        GameActions.Bottom.ModifyAllInstances(uuid, c -> GameUtilities.IncreaseMagicNumber(c, secondaryValue, false));
    }
}