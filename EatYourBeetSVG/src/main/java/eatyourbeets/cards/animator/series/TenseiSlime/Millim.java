package eatyourbeets.cards.animator.series.TenseiSlime;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.FireballEffect;
import eatyourbeets.cards.base.*;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameEffects;
import eatyourbeets.utilities.GameUtilities;

public class Millim extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Millim.class)
            .SetAttack(1, CardRarity.UNCOMMON, EYBAttackType.Elemental, EYBCardTarget.ALL)
            .SetSeriesFromClassPackage();

    public Millim()
    {
        super(DATA);

        Initialize(5, 5);
        SetUpgrade(4, 4);

        SetAffinity_Violet(2);

        SetUnique(true, true);
    }

    @Override
    protected void OnUpgrade()
    {
        if (timesUpgraded <= 4)
        {
            upgradeDamage(2);
            upgradeMagicNumber(0);
        }
        else
        {
            if (timesUpgraded == 5)
            {
                upgradeMagicNumber(-1);
            }

            upgradeDamage(1);
        }
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.DealDamageToAll(this, AttackEffects.BLUNT_HEAVY)
            .SetDamageEffect((enemy, __) ->  {
                CardCrawlGame.sound.play("ATTACK_FIRE", 0.2f);
                GameEffects.Queue.Add(new FireballEffect(player.hb.cX, player.hb.cY, enemy.hb.cX, enemy.hb.cY));
            });
        GameActions.Bottom.GainBlock(block);

        GameActions.Bottom.StackPower(new MillimPower(player, 1));
    }

    public static class MillimPower extends AnimatorPower {
        public MillimPower(AbstractPlayer owner, int amount)
        {
            super(owner, Millim.DATA);

            Initialize(amount);
        }

        @Override
        public void onPlayCard(AbstractCard card, AbstractMonster m) {
            super.onPlayCard(card, m);

            if (GameUtilities.IsHighCost(card)) {
                GameActions.Bottom.GainEnergy(1);
            }
        }
        @Override
        public void updateDescription() {
            description = FormatDescription(0, amount);
        }
    }
}