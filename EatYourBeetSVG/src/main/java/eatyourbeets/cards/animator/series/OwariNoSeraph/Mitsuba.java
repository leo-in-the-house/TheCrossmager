package eatyourbeets.cards.animator.series.OwariNoSeraph;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Mitsuba extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Mitsuba.class)
            .SetAttack(1, CardRarity.COMMON)
            .SetSeriesFromClassPackage();

    public Mitsuba()
    {
        super(DATA);

        Initialize(6, 6);
        SetUpgrade(2, 2);

        SetAffinity_Brown(1);
    }

    @Override
    public boolean cardPlayable(AbstractMonster m)
    {
        if (super.cardPlayable(m) && GameUtilities.InGame())
        {
            if (m == null) {
                for (AbstractMonster enemy : GameUtilities.GetEnemies(true)) {
                    if (enemy.currentHealth > player.currentHealth) {
                        return true;
                    }
                }
            }
            else if (m.currentHealth > player.currentHealth)
            {
                return true;
            }
        }

        return false;
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.GainBlock(block);
        GameActions.Bottom.DealDamage(this, m, AttackEffects.SLASH_HEAVY);
    }
}