package eatyourbeets.cards.animator.colorless.rare;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.special.Pyra;
import eatyourbeets.cards.base.*;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameEffects;
import eatyourbeets.utilities.GameUtilities;

public class Mythra extends AnimatorCard {
    public static final EYBCardData DATA = Register(Mythra.class)
            .SetSkill(1, CardRarity.RARE, EYBCardTarget.None)
            .SetSeries(CardSeries.XenobladeChronicles)
            .SetColor(CardColor.COLORLESS)
            .PostInitialize(data -> data.AddPreview(new Pyra(), false));

    public Mythra() {
        super(DATA);

        Initialize(0, 10, 0);
        SetUpgrade(0, 4, 0);

        SetAffinity_Red(1, 0, 1);
        SetAffinity_Green(1, 0, 1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.GainBlock(block);
    }

    @Override
    public void triggerWhenCreated(boolean startOfBattle)
    {
        super.triggerWhenCreated(startOfBattle);

        if (startOfBattle) {

            int amountRed = 0;
            int amountGreen = 0;
            for (AbstractCard card : player.masterDeck.group)
            {
                if (GameUtilities.HasRedAffinity(card)) {
                    amountRed++;
                }
                if (GameUtilities.HasGreenAffinity(card)) {
                    amountGreen++;
                }
            }

            if (amountRed > amountGreen) {
                GameEffects.List.ShowCopy(this, Settings.WIDTH * 0.75f, Settings.HEIGHT * 0.4f);
                GameActions.Bottom.ReplaceCard(uuid, new Pyra())
                        .SetUpgrade(upgraded);
            }
        }
    }
}