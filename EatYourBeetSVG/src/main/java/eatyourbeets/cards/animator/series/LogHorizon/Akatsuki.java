package eatyourbeets.cards.animator.series.LogHorizon;

import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.effects.AttackEffects;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.DieDieDieEffect;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.cards.base.EYBAttackType;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameEffects;

public class Akatsuki extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Akatsuki.class)
            .SetAttack(1, CardRarity.RARE, EYBAttackType.Piercing)
            .SetSeriesFromClassPackage();

    public Akatsuki()
    {
        super(DATA);

        Initialize(8, 0, 2);
        SetUpgrade(4, 0, 0);

        SetAffinity_Green(1, 0, 1);
        SetAffinity_Black(1, 0, 1);

        SetDelayed(true);
    }

    @Override
    public void triggerWhenDrawn()
    {
        super.triggerWhenDrawn();

        if (CombatStats.TryActivateSemiLimited(cardID))
        {
            for (int i = 0; i < magicNumber; i++)
            {
                GameActions.Top.MakeCardInHand(this)
                .SetUpgrade(false, true)
                .AddCallback(card ->
                {
                    card.purgeOnUse = true;
                    card.isEthereal = true;
                });
            }
        }
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.DealDamage(this, m, AttackEffects.SLASH_DIAGONAL)
        .SetDamageEffect(c -> GameEffects.List.Add(new DieDieDieEffect()).duration);
        GameActions.Bottom.CreateThrowingKnives(1);
    }
}