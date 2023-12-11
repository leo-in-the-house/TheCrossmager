package eatyourbeets.cards.animator.special;

import com.megacrit.cardcrawl.cards.AbstractCard;
import eatyourbeets.utilities.GameUtilities;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.colorless.uncommon.AiKizuna;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.interfaces.markers.Hidden;
import eatyourbeets.resources.GR;
import eatyourbeets.utilities.GameActions;

public class CardNotFound extends AnimatorCard implements Hidden
{
    public static final EYBCardData DATA = Register(CardNotFound.class)
            .SetSkill(0, CardRarity.COMMON, EYBCardTarget.None)
            .SetImagePath(GR.GetCardImage(AiKizuna.DATA.ID))
            .SetColor(CardColor.COLORLESS);

    public CardNotFound()
    {
        super(DATA);

        Initialize(0, 2);
        SetUpgrade(0, 2);

        SetRetain(true);
        SetPurge(true);

        this.cropPortrait = false;
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.GainBlock(block);
    }

    @Override
    public AbstractCard makeCopy()
    {
        final AbstractCard copy = super.makeCopy();
        copy.originalName = originalName;
        copy.name = name;
        return copy;
    }
}