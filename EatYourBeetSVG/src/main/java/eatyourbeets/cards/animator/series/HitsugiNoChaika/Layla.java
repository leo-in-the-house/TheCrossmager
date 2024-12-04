package eatyourbeets.cards.animator.series.HitsugiNoChaika;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.unique.BouncingFlaskAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.PotionBounceEffect;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBAttackType;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.effects.VFX;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameEffects;
import eatyourbeets.utilities.GameUtilities;

public class Layla extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Layla.class)
            .SetAttack(2, CardRarity.UNCOMMON, EYBAttackType.Ranged)
            .SetSeriesFromClassPackage();
    public static final int POISON_AMOUNT = 4;

    public Layla()
    {
        super(DATA);

        Initialize(7, 0, 3);
        SetUpgrade(4, 0, 3);

        SetAffinity_Pink(1, 0, 1);
        SetAffinity_Violet(1);

        SetDelayed(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.DealDamage(this, m, AttackEffects.NONE)
            .SetDamageEffect(c -> GameEffects.List.Add(VFX.ThrowDagger(c.hb, 0.15f).SetColor(Color.GREEN)).duration * 0.5f);

        GameActions.Bottom.Reload(name, cards ->
        {
            if (cards.size() > 0)
            {
                final AbstractMonster enemy = GameUtilities.GetRandomEnemy(true);
                if (enemy != null)
                {
                    GameActions.Top.VFX(new PotionBounceEffect(player.hb.cY, player.hb.cX, enemy.hb.cX, enemy.hb.cY), 0.3f);
                }

                GameActions.Top.Add(new BouncingFlaskAction(enemy, magicNumber, cards.size()));
            }
        });
    }
}