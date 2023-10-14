package eatyourbeets.cards.animator.series.Katanagatari;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import eatyourbeets.cards.base.*;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class ManiwaKyouken extends AnimatorCard
{
    public static final EYBCardData DATA = Register(ManiwaKyouken.class)
            .SetSkill(0, CardRarity.COMMON, EYBCardTarget.None)
            
            .SetSeries(CardSeries.Katanagatari);

    public ManiwaKyouken()
    {
        super(DATA);

        Initialize(0, 0, 2);
        SetUpgrade(0, 0, 0);

        SetAffinity_Violet(1);

        SetAffinityRequirement(Affinity.Black, 1);
    }

    @Override
    protected void OnUpgrade()
    {
        SetRetain(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.Draw(1)
            .AddCallback(cards -> {
                for (AbstractCard card : cards) {

                    if (GameUtilities.IsSealed(card)) {
                        CombatStats.Affinities.AddAffinitySealUses(magicNumber);
                    }

                    GameActions.Top.SealAffinities(card);
                }
            });
    }

    @Override
    public void OnLateUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameActions.Bottom.Callback(() ->
        {
            final int discard = EnergyPanel.getCurrentEnergy() * 2;
            if (discard > 0)
            {
                GameActions.Bottom.DiscardFromHand(name, discard, false)
                .SetOptions(false, false, false);
            }
        });

        if (CheckSpecialCondition(false))
        {
            SetAffinityRequirement(Affinity.Black, affinities.GetRequirement(Affinity.Black) + 1);
        }
        else
        {
            this.exhaustOnUseOnce = true;
        }
    }
}