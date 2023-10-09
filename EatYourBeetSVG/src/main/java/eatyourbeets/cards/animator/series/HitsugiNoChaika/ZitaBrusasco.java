package eatyourbeets.cards.animator.series.HitsugiNoChaika;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.Frost;
import com.megacrit.cardcrawl.orbs.Lightning;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class ZitaBrusasco extends AnimatorCard
{
    public static final EYBCardData DATA = Register(ZitaBrusasco.class)
            .SetSkill(0, CardRarity.COMMON, EYBCardTarget.None)
            .SetSeriesFromClassPackage();

    public ZitaBrusasco()
    {
        super(DATA);

        Initialize(0, 0, 2);
        SetUpgrade(0, 0, 2);

        SetAffinity_Blue(1, 0, 0);

        SetCooldown(1, 0, this::OnCooldownCompleted);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.GainBlock(block);

        cooldown.ProgressCooldownAndTrigger(m);
    }

    protected void OnCooldownCompleted(AbstractMonster m)
    {
        GameActions.Bottom.ChannelOrb(new Lightning())
            .AddCallback(orbs -> {
                for (AbstractOrb orb : orbs) {
                    GameActions.Top.TriggerOrbPassive(orb, magicNumber);
                }
            });

        GameActions.Bottom.ChannelOrb(new Frost())
            .AddCallback(orbs -> {
                for (AbstractOrb orb : orbs) {
                    GameActions.Top.TriggerOrbPassive(orb, magicNumber);
                }
            });
    }
}