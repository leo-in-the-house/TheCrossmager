package eatyourbeets.cards.animator.series.TenseiSlime;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.FireballEffect;
import com.megacrit.cardcrawl.vfx.combat.ScreenOnFireEffect;
import eatyourbeets.cards.base.*;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameEffects;
import eatyourbeets.utilities.GameUtilities;

public class Millim extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Millim.class)
            .SetAttack(2, CardRarity.UNCOMMON, EYBAttackType.Elemental, EYBCardTarget.ALL)
            .SetSeriesFromClassPackage();

    public Millim()
    {
        super(DATA);

        Initialize(5, 5, 2);
        SetUpgrade(2, 2, 1);

        SetAffinity_Violet(2);

        SetEthereal(true);

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

        final ScreenOnFireEffect effect = new ScreenOnFireEffect();
        effect.duration = effect.startingDuration = 0.5f;
        GameActions.Bottom.VFX(effect, 0.2f);

        GameActions.Bottom.DealDamageToAll(this, AttackEffects.BLUNT_HEAVY)
            .SetDamageEffect((enemy, __) ->  {
                CardCrawlGame.sound.play("ATTACK_FIRE", 0.2f);
                GameEffects.Queue.Add(new FireballEffect(player.hb.cX, player.hb.cY, enemy.hb.cX, enemy.hb.cY));
            });
        GameActions.Bottom.GainBlock(block);

        GameActions.Bottom.StackPower(new MillimPower(player, magicNumber));
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
        public void atEndOfTurn(boolean isPlayer)
        {
            super.atEndOfTurn(isPlayer);

            ReducePower(1);
        }

        @Override
        public void updateDescription() {
            description = FormatDescription(0, amount);
        }
    }
}