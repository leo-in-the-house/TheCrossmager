package eatyourbeets.cards.animator.series.GenshinImpact;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.Dark;
import com.megacrit.cardcrawl.orbs.Frost;
import com.megacrit.cardcrawl.orbs.Lightning;
import com.megacrit.cardcrawl.orbs.Plasma;
import eatyourbeets.cards.base.*;
import eatyourbeets.orbs.animator.Aether;
import eatyourbeets.orbs.animator.Chaos;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class RaidenShogun extends AnimatorCard {
    public static final EYBCardData DATA = Register(RaidenShogun.class)
            .SetAttack(4, CardRarity.RARE, EYBAttackType.Elemental, EYBCardTarget.ALL)
            .SetSeriesFromClassPackage();

    public RaidenShogun() {
        super(DATA);

        Initialize(9, 0, 0);
        SetUpgrade(0, 0, 0);
        SetCostUpgrade(-1);

        SetAffinity_Yellow(2, 0, 1);
        SetAffinity_Pink(2, 0, 1);

        SetEthereal(true);
    }

    @Override
    public void triggerWhenDrawn() {
        super.triggerWhenDrawn();

        if (CombatStats.TryActivateLimited(cardID)) {
            GameActions.Bottom.GainOrbSlots(1);
        }
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.DealDamageToAll(this, AbstractGameAction.AttackEffect.LIGHTNING);

        int orbSlots = player.maxOrbs;

        if (orbSlots >= 1) {
            GameActions.Bottom.ChannelOrb(new Lightning());
        }
        if (orbSlots >= 2) {
            GameActions.Bottom.ChannelOrb(new Dark());
        }
        if (orbSlots >= 3) {
            GameActions.Bottom.ChannelOrb(new Frost());
        }
        if (orbSlots >= 4) {
            GameActions.Bottom.ChannelOrb(new Aether());
        }
        if (orbSlots >= 5) {
            GameActions.Bottom.ChannelOrb(new Chaos());
        }
        if (orbSlots >= 6) {
            GameActions.Bottom.ChannelOrb(new Plasma());
        }
    }
}