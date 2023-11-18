package eatyourbeets.cards.animator.series.TenseiSlime;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.interfaces.subscribers.OnPlayCardSubscriber;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

import java.util.ArrayList;

public class Ranga extends AnimatorCard implements OnPlayCardSubscriber
{
    public static final EYBCardData DATA = Register(Ranga.class)
            .SetAttack(0, CardRarity.UNCOMMON, EYBAttackType.Elemental)
            .SetSeriesFromClassPackage();

    public Ranga()
    {
        super(DATA);

        Initialize(4, 0, 1);
        SetUpgrade(1, 0, 0);

        SetAffinity_Yellow(1, 0, 1);
        SetAffinity_Black(1, 0, 1);

        SetExhaust(true);
    }


    @Override
    public AbstractAttribute GetDamageInfo()
    {
        return super.GetDamageInfo().AddMultiplier(magicNumber);
    }

    @Override
    protected void OnUpgrade()
    {
        SetAttackTarget(EYBCardTarget.ALL);
        SetMultiDamage(true);
        upgradedDamage = true;
    }

    @Override
    public void OnPlayCard(AbstractCard card, AbstractMonster m)
    {
        if (player.exhaustPile.contains(this) && GameUtilities.IsHighCost(card))
        {
            ArrayList<AbstractCard> cardsPlayed = AbstractDungeon.actionManager.cardsPlayedThisTurn;
            int numHighCostCardsPlayed = 0;

            for (AbstractCard c : cardsPlayed) {
                if (GameUtilities.IsHighCost(c)) {
                    numHighCostCardsPlayed++;
                }
            }

            if (numHighCostCardsPlayed == 1) {
                GameActions.Last.MoveCard(this, player.exhaustPile, player.hand)
                        .AddCallback(c -> {
                            GameUtilities.IncreaseMagicNumber(this, 1, false);
                        });
            }
        }
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);

        for (int i=0; i<magicNumber; i++) {
            if (upgraded)
            {
                GameActions.Bottom.DealDamageToAll(this, AttackEffects.LIGHTNING);
            }
            else
            {
                GameActions.Bottom.DealDamage(this, m, AttackEffects.LIGHTNING);
            }
        }
    }

    @Override
    public void triggerWhenCreated(boolean startOfBattle)
    {
        super.triggerWhenCreated(startOfBattle);

        CombatStats.onPlayCard.Subscribe(this);
    }
}