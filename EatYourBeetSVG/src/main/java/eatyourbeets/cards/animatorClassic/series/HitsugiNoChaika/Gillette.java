package eatyourbeets.cards.animatorClassic.series.HitsugiNoChaika;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.EnergizedPower;
import eatyourbeets.cards.base.AnimatorClassicCard;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.TargetHelper;

public class Gillette extends AnimatorClassicCard
{
    public static final EYBCardData DATA = Register(Gillette.class).SetSeriesFromClassPackage().SetAttack(1, CardRarity.COMMON);

    public Gillette()
    {
        super(DATA);

        Initialize(7, 0, 1);
        SetUpgrade(3, 0, 0);

        
    }

    @Override
    public void triggerOnManualDiscard()
    {
        super.triggerOnManualDiscard();

        GameActions.Bottom.ApplyWeak(TargetHelper.Enemies(), magicNumber);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.DealDamage(this, m, AttackEffects.SLASH_DIAGONAL);
        GameActions.Bottom.StackPower(new EnergizedPower(p, 1));
    }
}