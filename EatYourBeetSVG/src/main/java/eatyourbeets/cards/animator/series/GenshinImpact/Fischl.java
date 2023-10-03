package eatyourbeets.cards.animator.series.GenshinImpact;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.special.Fischl_Oz;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBAttackType;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Fischl extends AnimatorCard {
    public static final EYBCardData DATA = Register(Fischl.class).SetAttack(1, CardRarity.UNCOMMON, EYBAttackType.Elemental).SetSeriesFromClassPackage()
            .PostInitialize(data -> data.AddPreview(new Fischl_Oz(), false));

    public Fischl() {
        super(DATA);

        Initialize(3, 0, 0);
        SetUpgrade(2, 0, 0);

        SetAffinity_Yellow(1, 0, 1);

        SetHaste(true);
        SetEthereal(true);
    }

    @Override
    public void triggerWhenDrawn()
    {
        if (this.hasTag(HASTE))
        {
            GameActions.Top.Discard(this, player.hand).ShowEffect(true, true)
                    .AddCallback(() -> GameActions.Top.MakeCardInHand(new Fischl_Oz())
                    .SetUpgrade(upgraded, false))
                    .SetDuration(0.15f, true);
        }
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.DealDamage(this, m, AttackEffects.DARK);
        GameActions.Bottom.GainOrbSlots(1);
    }
}