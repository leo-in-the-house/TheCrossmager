package eatyourbeets.cards.animator.colorless.uncommon;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.Lightning;
import eatyourbeets.cards.base.*;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class LestKarr extends AnimatorCard
{
    public static final EYBCardData DATA = Register(LestKarr.class)
            .SetSkill(2, CardRarity.UNCOMMON, EYBCardTarget.None)
            .SetColor(CardColor.COLORLESS)
            .SetSeries(CardSeries.OwariNoSeraph);

    public LestKarr()
    {
        super(DATA);

        Initialize(0, 0, 0);
        SetCostUpgrade(-1);

        SetAffinity_Yellow(1);
        SetAffinity_Black(1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        ChannelTriggerEvokeLightning();

        GameActions.Bottom.ExhaustFromHand(name, player.hand.size(), false)
            .SetOptions(true, true, true)
            .AddCallback(cards -> {
                for (AbstractCard card : cards) {
                    ChannelTriggerEvokeLightning();
                }
            });
    }

    private void ChannelTriggerEvokeLightning() {
        GameActions.Bottom.ChannelOrb(new Lightning())
            .AddCallback(orbs -> {
                for (AbstractOrb orb : orbs) {
                    GameActions.Top.EvokeOrb(1, orb);
                    GameActions.Top.TriggerOrbPassive(orb, 1);
                }
            });
    }
}