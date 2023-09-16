package eatyourbeets.cards.animator.series.FullmetalAlchemist;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.Frost;
import com.megacrit.cardcrawl.orbs.Lightning;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBAttackType;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.orbs.animator.Earth;
import eatyourbeets.orbs.animator.Water;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class ElricEdward extends AnimatorCard
{
    public static final EYBCardData DATA = Register(ElricEdward.class)
            .SetAttack(1, CardRarity.UNCOMMON, EYBAttackType.Elemental)
            .SetSeriesFromClassPackage();

    public ElricEdward()
    {
        super(DATA);

        Initialize(4, 0, 0);
        SetUpgrade(4, 0, 0);

        SetAffinity_Blue(1, 0, 1);
        SetAffinity_White(1);

        SetEvokeOrbCount(1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.DealDamage(this, m, AttackEffects.FIRE);
    }

    @Override
    public void OnLateUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameActions.Bottom.Cycle(name, 1).AddCallback(cards ->
        {
            for (AbstractCard c : cards)
            {
                switch (c.type)
                {
                    case ATTACK:
                        GameActions.Bottom.ChannelOrb(new Lightning());
                        return;

                    case SKILL:
                        GameActions.Bottom.ChannelOrb(new Frost());
                        return;

                    case POWER:
                        GameActions.Bottom.ChannelOrb(new Earth());
                        return;

                    case CURSE:
                    case STATUS:
                        GameActions.Bottom.ChannelOrb(new Water());
                        return;
                }
            }
        });
    }
}