package eatyourbeets.cards.animator.series.Katanagatari;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.special.Emonzaemon_EntouJyuu;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBAttackType;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Emonzaemon extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Emonzaemon.class)
            .SetAttack(0, CardRarity.UNCOMMON, EYBAttackType.Ranged)
            .SetSeriesFromClassPackage()
            .PostInitialize(data -> data.AddPreview(new Emonzaemon_EntouJyuu(), true));

    public Emonzaemon()
    {
        super(DATA);

        Initialize(1, 0, 2);
        SetUpgrade(2, 0);

        SetAffinity_Black(1, 0, 1);
    }

    @Override
    public AbstractAttribute GetDamageInfo()
    {
        return super.GetDamageInfo().AddMultiplier(magicNumber);
    }

    @Override
    public void triggerOnAffinitySeal(boolean reshuffle)
    {
        super.triggerOnAffinitySeal(reshuffle);

        GameActions.Last.ReplaceCard(uuid, new Emonzaemon_EntouJyuu());
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.DealDamage(this, m, AttackEffects.GUNSHOT).SetSoundPitch(0.55f, 0.65f);
        GameActions.Bottom.WaitRealtime(0.25f);
        GameActions.Bottom.DealDamage(this, m, AttackEffects.GUNSHOT).SetSoundPitch(0.55f, 0.65f);
    }
}