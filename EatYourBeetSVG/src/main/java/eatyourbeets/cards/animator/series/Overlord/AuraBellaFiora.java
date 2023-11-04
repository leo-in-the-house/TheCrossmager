package eatyourbeets.cards.animator.series.Overlord;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.TargetHelper;

public class AuraBellaFiora extends AnimatorCard
{
    public static final EYBCardData DATA = Register(AuraBellaFiora.class)
            .SetAttack(0, CardRarity.UNCOMMON, EYBAttackType.Normal, EYBCardTarget.Normal)
            .SetSeriesFromClassPackage();

    public AuraBellaFiora()
    {
        super(DATA);

        Initialize(5, 0, 6);
        SetUpgrade(1, 0, -2);

        SetAffinity_Green(1, 0, 1);
    }

    @Override
    public boolean cardPlayable(AbstractMonster m) {
        if (super.cardPlayable(m)) {
            for (AbstractMonster enemy : GameUtilities.GetEnemies(true)) {
                if (GameUtilities.GetCommonDebuffs(TargetHelper.Normal(enemy)).size() >= magicNumber) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.DealDamage(this, m, AttackEffects.CLAW);

        int handSize = player.hand.size();

        for (AbstractCard card : player.hand.group) {
            GameActions.Bottom.Discard(card, player.hand);
        }

        GameActions.Bottom.Callback(() -> {
           GameActions.Top.FetchFromPile(name, handSize + 1, player.discardPile)
                   .ShowEffect(true, true)
                   .SetOptions(true, true);
        });
    }
}