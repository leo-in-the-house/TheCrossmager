package eatyourbeets.cards.animatorClassic.series.GATE;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorClassicCard;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.powers.animatorClassic.BozesPower;
import eatyourbeets.utilities.GameActions;

public class Bozes extends AnimatorClassicCard
{
    public static final EYBCardData DATA = Register(Bozes.class).SetSeriesFromClassPackage().SetAttack(2, CardRarity.UNCOMMON);

    public Bozes()
    {
        super(DATA);

        Initialize(7, 0, 2, 1);
        SetUpgrade(0, 0, 1);

        SetExhaust(true);

    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.DealDamage(this, m, AttackEffects.SLASH_VERTICAL);
        GameActions.Bottom.Motivate(magicNumber);
        GameActions.Bottom.StackPower(new BozesPower(p, this.secondaryValue));
    }
}