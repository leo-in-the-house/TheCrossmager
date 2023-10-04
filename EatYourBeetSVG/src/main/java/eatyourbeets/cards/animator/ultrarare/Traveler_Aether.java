package eatyourbeets.cards.animator.ultrarare;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;
import eatyourbeets.cards.base.*;
import eatyourbeets.orbs.animator.Aether;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

import java.util.LinkedList;
import java.util.List;

public class Traveler_Aether extends AnimatorCard_UltraRare {
    public static final EYBCardData DATA = Register(Traveler_Aether.class)
            .SetAttack(1, CardRarity.SPECIAL, EYBAttackType.Normal, EYBCardTarget.Normal)
            .SetColor(CardColor.COLORLESS)
            .SetSeries(CardSeries.GenshinImpact);

    public Traveler_Aether() {
        super(DATA);

        Initialize(9, 0, 0);
        SetUpgrade(0, 0, 0);
        SetCostUpgrade(-1);

        SetAffinity_Blue(2, 0, 2);
        SetAffinity_Green(2, 0, 2);
    }

    @Override
    public void triggerWhenDrawn() {
        super.triggerWhenDrawn();

        if (CombatStats.TryActivateLimited(cardID)) {
            GameActions.Bottom.ChannelOrb(new Aether());
        }
    }
    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.DealDamage(this, m, AbstractGameAction.AttackEffect.SLASH_HEAVY);

        List<AbstractOrb> evokedOrbs = new LinkedList<>();

        for (AbstractOrb orb : player.orbs) {
            if (orb != null && !(orb instanceof EmptyOrbSlot)) {
                evokedOrbs.add(orb.makeCopy());
                GameActions.Bottom.EvokeOrb(2, orb);
            }
        }

        for (AbstractOrb orb : evokedOrbs) {
            GameActions.Bottom.ChannelOrb(orb);
        }
    }
}