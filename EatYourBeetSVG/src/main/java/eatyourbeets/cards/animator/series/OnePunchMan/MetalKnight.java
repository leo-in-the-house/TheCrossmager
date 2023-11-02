package eatyourbeets.cards.animator.series.OnePunchMan;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.Plasma;
import com.megacrit.cardcrawl.vfx.combat.WeightyImpactEffect;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBAttackType;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class MetalKnight extends AnimatorCard
{
    public static final EYBCardData DATA = Register(MetalKnight.class)
            .SetAttack(3, CardRarity.UNCOMMON, EYBAttackType.Ranged)
            
            .SetSeriesFromClassPackage();

    public MetalKnight()
    {
        super(DATA);

        Initialize(4, 0, 4, 1);
        SetUpgrade(0, 0, 2, 1);

        SetAffinity_Teal(2, 0, 2);
        SetAffinity_Yellow(1, 0, 1);

        SetInnate(true);
    }

    @Override
    public AbstractAttribute GetDamageInfo()
    {
        return super.GetDamageInfo().AddMultiplier(magicNumber);
    }

    @Override
    public void triggerOnManualDiscard()
    {
        super.triggerOnManualDiscard();

        GameActions.Bottom.GainMetallicize(secondaryValue);
    }

    @Override
    public void triggerOnExhaust() {
        super.triggerOnExhaust();

        GameActions.Bottom.GainMetallicize(secondaryValue);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        for (int i=0; i<magicNumber; i++) {
            GameActions.Bottom.VFX(new WeightyImpactEffect(m.hb.cX, m.hb.cY), 0.6f, true);
            GameActions.Bottom.DealDamage(this, m, AttackEffects.BLUNT_HEAVY);
            GameActions.Bottom.Wait(0.2f);
        }

        GameActions.Bottom.DiscardFromHand(name, 1, false)
            .SetFilter(GameUtilities::HasAnyScaling)
            .SetOptions(true, true, true)
            .AddCallback(cards -> {
                if (cards.size() > 0) {
                    GameActions.Top.ChannelOrb(new Plasma());
                }
            });
    }

}