package eatyourbeets.cards.animatorClassic.series.HitsugiNoChaika;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animatorClassic.special.ThrowingKnife;
import eatyourbeets.cards.base.AnimatorClassicCard;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.utilities.GameActions;

public class AcuraTooru extends AnimatorClassicCard
{
    public static final EYBCardData DATA = Register(AcuraTooru.class).SetSeriesFromClassPackage().SetAttack(2, CardRarity.UNCOMMON);
    static
    {
        for (ThrowingKnife knife : ThrowingKnife.GetAllCards())
        {
            DATA.AddPreview(knife, false);
        }
    }

    public AcuraTooru()
    {
        super(DATA);

        Initialize(3, 0, 2, 2);
        SetUpgrade(0, 0, 0, 1);
        SetScaling(0, 1, 0);

        

    }

    @Override
    public AbstractAttribute GetDamageInfo()
    {
        return super.GetDamageInfo().AddMultiplier(2);
    }

    @Override
    public void triggerOnManualDiscard()
    {
        super.triggerOnManualDiscard();

        GameActions.Bottom.GainBlock(magicNumber);
        GameActions.Bottom.GainGreen(1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.DealDamage(this, m, AttackEffects.SLASH_VERTICAL);
        GameActions.Bottom.DealDamage(this, m, AttackEffects.SLASH_DIAGONAL);
        GameActions.Bottom.CreateThrowingKnives(secondaryValue);

        if (info.IsSynergizing)
        {
            GameActions.Bottom.GainBlock(magicNumber);
            GameActions.Bottom.GainGreen(1);
        }
    }
}