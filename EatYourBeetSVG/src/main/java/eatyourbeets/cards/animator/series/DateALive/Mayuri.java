package eatyourbeets.cards.animator.series.DateALive;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.effects.SFX;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Mayuri extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Mayuri.class)
            .SetAttack(2, CardRarity.COMMON, EYBAttackType.Normal, EYBCardTarget.ALL)
            .SetSeriesFromClassPackage();

    public Mayuri()
    {
        super(DATA);

        Initialize(11, 0);
        SetUpgrade(8, 0);

        SetAffinity_Red(1);
        SetAffinity_Light(1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);

        int[] damageMatrix = DamageInfo.createDamageMatrix(damage, true);
        GameActions.Bottom.DealDamageToAll(damageMatrix, DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.LIGHTNING);
        GameActions.Bottom.SFX(SFX.ORB_LIGHTNING_EVOKE);

        GameActions.Bottom.Callback(cards -> {
                for (AbstractCard card : GameUtilities.GetOtherCardsInHand(this))
                {
                    if (card.tags.contains(CardTags.STARTER_DEFEND))
                    {
                        GameUtilities.Retain(card);
                    }
                }
        });
    }
}