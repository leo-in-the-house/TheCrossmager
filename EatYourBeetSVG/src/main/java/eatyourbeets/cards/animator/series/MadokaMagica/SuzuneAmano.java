package eatyourbeets.cards.animator.series.MadokaMagica;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBAttackType;
import eatyourbeets.orbs.animator.Fire;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.utilities.GameActions;

public class SuzuneAmano extends AnimatorCard
{
    public static final EYBCardData DATA = Register(SuzuneAmano.class)
            .SetAttack(2, CardRarity.COMMON, EYBAttackType.Elemental)
            
            .SetSeriesFromClassPackage();

    public SuzuneAmano()
    {
        super(DATA);

        Initialize(6, 0, 2);
        SetUpgrade(8, 0);

        SetAffinity_Red(2);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.DealDamage(this, m, AttackEffects.FIRE);
        GameActions.Bottom.ExhaustFromPile(name, 1, p.hand)
        .SetOptions(false, false, false)
            .AddCallback(cards -> {
                if (cards.size() > 0) {
                    GameActions.Top.ChannelOrbs(Fire::new, magicNumber);
                }
            });
    }
}