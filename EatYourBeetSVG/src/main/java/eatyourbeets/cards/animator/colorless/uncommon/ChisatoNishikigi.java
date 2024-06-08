package eatyourbeets.cards.animator.colorless.uncommon;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.cards.base.attributes.TempHPAttribute;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class ChisatoNishikigi extends AnimatorCard {
    public static final EYBCardData DATA = Register(ChisatoNishikigi.class)
            .SetSkill(3, CardRarity.UNCOMMON, EYBCardTarget.None)
            .SetColor(CardColor.COLORLESS)
            .SetSeries(CardSeries.LycorisRecoil);

    static
    {
        DATA.AddPreview(new TakinaInoue(), true);
    }

    public ChisatoNishikigi() {
        super(DATA);

        Initialize(0, 6, 4);
        SetUpgrade(0, 0, 0);
        SetAffinity_Green(1, 0, 1);
        SetAffinity_White(1);
    }

    @Override
    public AbstractAttribute GetSpecialInfo()
    {
        return TempHPAttribute.Instance.SetCard(this, true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.GainBlock(block);
        GameActions.Bottom.GainTemporaryHP(magicNumber);


        GameActions.Bottom.SelectFromHand(name, magicNumber, false)
                .SetOptions(true, true, true)
                .SetFilter(GameUtilities::IsHindrance)
                .SetMessage(cardData.Strings.EXTENDED_DESCRIPTION[0])
                .AddCallback(cards ->
                {
                    if (cards.size() > 0) {
                        GameActions.Top.ReplaceCard(cards.get(0).uuid, new TakinaInoue());
                        GameActions.Top.GainEnergy(2);
                    }
                });
    }
}