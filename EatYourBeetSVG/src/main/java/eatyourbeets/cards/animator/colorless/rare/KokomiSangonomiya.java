package eatyourbeets.cards.animator.colorless.rare;

import com.evacipated.cardcrawl.mod.stslib.actions.defect.EvokeSpecificOrbAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardSeries;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.orbs.animator.Water;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.utilities.GameActions;

import java.util.ArrayList;

public class KokomiSangonomiya extends AnimatorCard
{
    public static final EYBCardData DATA = Register(KokomiSangonomiya.class)
            .SetPower(2, CardRarity.RARE)
            .SetColor(CardColor.COLORLESS)
            .SetSeries(CardSeries.GenshinImpact);

    public KokomiSangonomiya()
    {
        super(DATA);

        Initialize(0, 0, 2);
        SetUpgrade(0, 0, 2);

        SetAffinity_Blue(2);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameActions.Bottom.ChannelOrbs(Water::new, 2);
        GameActions.Bottom.StackPower(new KokomiPower(player));
    }

    public static class KokomiPower extends AnimatorPower
    {
        public KokomiPower(AbstractPlayer owner)
        {
            super(owner, KokomiSangonomiya.DATA);

            this.priority = -99;

            Initialize(1);
        }

        @Override
        public int onAttackedToChangeDamage(DamageInfo info, int damageAmount)
        {
            final ArrayList<AbstractOrb> waterOrbs = new ArrayList<>();
            for (AbstractOrb orb : player.orbs)
            {
                if (Water.ORB_ID.equals(orb.ID) && orb.evokeAmount > 0)
                {
                    waterOrbs.add(orb);
                }
            }

            if (waterOrbs.size() > 0)
            {
                if (damageAmount > 0)
                {
                    damageAmount = AbsorbDamage(damageAmount, waterOrbs);
                    flashWithoutSound();
                }
            }

            return super.onAttackedToChangeDamage(info, damageAmount);
        }

        private int AbsorbDamage(int damage, ArrayList<AbstractOrb> waterOrbs)
        {
            final AbstractOrb next = waterOrbs.get(0);
            final float temp = damage;

            damage = Math.max(0, damage - next.evokeAmount);
            next.evokeAmount -= temp;

            if (next.evokeAmount <= 0)
            {
                waterOrbs.remove(next);
                next.evokeAmount = 0;
                GameActions.Top.Add(new EvokeSpecificOrbAction(next));
            }

            return (damage > 0 && waterOrbs.size() > 0) ? AbsorbDamage(damage, waterOrbs) : damage;
        }
    }
}