package eatyourbeets.cards.animator.series.GoblinSlayer;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.stances.NeutralStance;
import eatyourbeets.cards.base.*;
import eatyourbeets.cards.effects.GenericEffects.GenericEffect_EnterStance;
import eatyourbeets.stances.CalmStance;
import eatyourbeets.stances.MagicStance;
import eatyourbeets.stances.TranceStance;
import eatyourbeets.stances.WrathStance;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Witch extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Witch.class)
            .SetSkill(2, CardRarity.UNCOMMON, EYBCardTarget.ALL)
            .SetSeriesFromClassPackage();
    private static final CardEffectChoice choices = new CardEffectChoice();

    public Witch()
    {
        super(DATA);

        Initialize(0, 11);
        SetUpgrade(0, 8);

        SetAffinity_Blue(1, 0, 1);
        SetAffinity_Pink(1, 0, 1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.GainBlock(block);

        GameActions.Bottom.ExhaustFromHand(name, 1, false)
                .SetFilter(GameUtilities::IsHindrance)
                .SetOptions(true, true, true)
                .AddCallback(cards -> {
                    if (cards.size() > 0) {
                        if (choices.TryInitialize(this))
                        {
                            choices.AddEffect(new GenericEffect_EnterStance(WrathStance.STANCE_ID));
                            choices.AddEffect(new GenericEffect_EnterStance(TranceStance.STANCE_ID));
                            choices.AddEffect(new GenericEffect_EnterStance(MagicStance.STANCE_ID));
                            choices.AddEffect(new GenericEffect_EnterStance(CalmStance.STANCE_ID));
                            choices.AddEffect(new GenericEffect_EnterStance(NeutralStance.STANCE_ID));
                            choices.Initialize(this);
                        }

                        choices.Select(1, m);
                    }
                });
    }
}