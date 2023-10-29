package eatyourbeets.cards.animator.series.MadokaMagica;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.curse.special.Curse_GriefSeed;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBAttackType;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.RandomizedList;

public class YuiTsuruno extends AnimatorCard
{
    public static final EYBCardData DATA = Register(YuiTsuruno.class)
            .SetAttack(0, CardRarity.COMMON, EYBAttackType.Elemental)
            .SetSeriesFromClassPackage();

    public YuiTsuruno()
    {
        super(DATA);

        Initialize(8, 0);
        SetUpgrade(3, 0);

        SetFading(true);
        SetAffinity_Yellow(1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.DealDamage(this, m, AttackEffects.FIRE);

        GameActions.Bottom.SelectFromHand(name, 1, false)
            .SetOptions(false, false, false)
            .AddCallback(info, (info2, cards) ->
            {
                final RandomizedList<AbstractCard> toExhaust = new RandomizedList<>();
                for (AbstractCard c : player.hand.group)
                {
                    if (!cards.contains(c) && !c.uuid.equals(uuid))
                    {
                        toExhaust.Add(c);
                    }
                }

                if (toExhaust.Size() > 0) {
                    GameActions.Delayed.Exhaust(toExhaust.Retrieve(rng), player.hand)
                            .ShowEffect(true, true, 0.1f);
                    GameActions.Delayed.MakeCardInHand(new Curse_GriefSeed());
                }
            });
    }
}