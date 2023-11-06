package eatyourbeets.cards.animator.series.GenshinImpact;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.RegenPower;
import eatyourbeets.cards.base.*;
import eatyourbeets.interfaces.subscribers.OnAffinitySealedSubscriber;
import eatyourbeets.orbs.animator.Water;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Furina extends AnimatorCard implements OnAffinitySealedSubscriber {
    public static final EYBCardData DATA = Register(Furina.class)
            .SetSkill(-1, CardRarity.RARE, EYBCardTarget.None)
            .SetSeriesFromClassPackage();

    public Furina() {
        super(DATA);

        Initialize(0, 0, 2);
        SetUpgrade(0, 0, 1);

        SetExhaust(true);

        SetAffinity_Blue(2);
        SetMultiDamage(true);
    }

    @Override
    protected void OnUpgrade()
    {
        SetExhaust(false);
    }

    @Override
    public void OnAffinitySealed(EYBCard card, boolean manual) {
        if (card.uuid.equals(uuid) && CombatStats.TryActivateLimited(cardID)) {
            int amount = GameUtilities.GetUniqueOrbsCount();

            if (amount > 0) {
                GameActions.Bottom.StackPower(new RegenPower(player, amount));
                GameActions.Bottom.GainArtifact(amount);
            }
        }
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        int stacks = GameUtilities.UseXCostEnergy(this);

        if (stacks >= 0) {
            GameActions.Bottom.ChannelOrb(new Water())
                .AddCallback(orbs -> {
                    for (AbstractOrb orb : orbs) {
                        GameActions.Top.TriggerOrbPassive(orb, stacks * magicNumber);
                    }
                });
        }
    }

    @Override
    public void triggerWhenCreated(boolean startOfBattle)
    {
        super.triggerWhenCreated(startOfBattle);

        CombatStats.onAffinitySealed.Subscribe(this);
    }
}