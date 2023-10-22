package eatyourbeets.cards.animator.series.LogHorizon;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.Dark;
import com.megacrit.cardcrawl.orbs.Frost;
import com.megacrit.cardcrawl.orbs.Lightning;
import com.megacrit.cardcrawl.orbs.Plasma;
import eatyourbeets.cards.base.*;
import eatyourbeets.cards.effects.GenericEffects.GenericEffect_ChannelOrb;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.orbs.animator.*;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.RandomizedList;

public class RundelhausCode extends AnimatorCard
{
    public static final EYBCardData DATA = Register(RundelhausCode.class)
            .SetAttack(1, CardRarity.UNCOMMON, EYBAttackType.Elemental, EYBCardTarget.ALL)
            .SetSeries(CardSeries.LogHorizon);

    public RundelhausCode()
    {
        super(DATA);

        Initialize(11, 0, 3);
        SetUpgrade(8, 0);

        SetAffinity_Red(1, 0, 1);
        SetAffinity_Blue(1, 0, 1);

        SetDelayed(true);
        SetExhaust(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.DealDamageToAll(this, AttackEffects.LIGHTNING);

        int numEmptyOrbs = GameUtilities.GetEmptyOrbCount();

        for (int i=0; i<numEmptyOrbs; i++) {
            CardEffectChoice choices = new CardEffectChoice();
            RandomizedList<GenericEffect_ChannelOrb> potentialChoices = new RandomizedList<>();
            potentialChoices.Add(new GenericEffect_ChannelOrb(new Fire()));
            potentialChoices.Add(new GenericEffect_ChannelOrb(new Lightning()));
            potentialChoices.Add(new GenericEffect_ChannelOrb(new Frost()));
            potentialChoices.Add(new GenericEffect_ChannelOrb(new Aether()));
            potentialChoices.Add(new GenericEffect_ChannelOrb(new Earth()));
            potentialChoices.Add(new GenericEffect_ChannelOrb(new Water()));
            potentialChoices.Add(new GenericEffect_ChannelOrb(new Dark()));
            potentialChoices.Add(new GenericEffect_ChannelOrb(new Chaos()));
            potentialChoices.Add(new GenericEffect_ChannelOrb(new Plasma()));

            if (choices.TryInitialize(this))
            {
                for (int j=0; j<magicNumber; j++) {
                    choices.AddEffect(potentialChoices.Retrieve(rng, true));
                }
            }

            choices.Select(GameActions.Bottom, 1, null)
                    .CancellableFromPlayer(true);
        }
    }
}