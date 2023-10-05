package eatyourbeets.cards.animator.colorless.uncommon;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.Lightning;
import eatyourbeets.cards.base.*;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class LisaMinci extends AnimatorCard {
    public static final EYBCardData DATA = Register(LisaMinci.class)
            .SetSkill(1, CardRarity.UNCOMMON, EYBCardTarget.Self)
            .SetColor(CardColor.COLORLESS)
            .SetSeries(CardSeries.GenshinImpact);

    public LisaMinci() {
        super(DATA);

        Initialize(0, 0, 3, 0);
        SetUpgrade(0, 0, 2, 0);

        SetAffinity_Blue(1);
        SetAffinity_Yellow(1);
    }

    @Override
    protected void SetUpgrade(int damage, int block) {
        super.SetUpgrade(damage, block);

        SetRetain(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.Scry(magicNumber)
            .AddCallback(cards -> {
                for (AbstractCard card : cards) {
                    if (card.rarity == CardRarity.UNCOMMON || card.rarity == CardRarity.RARE) {
                        GameActions.Top.ChannelOrb(new Lightning());
                    }
                }
            });
    }
}