package eatyourbeets.cards.animatorClassic.colorless.rare;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import eatyourbeets.cards.base.*;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.powers.PowerHelper;
import eatyourbeets.powers.animator.GeassPower;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.TargetHelper;

public class Lelouch extends AnimatorClassicCard
{
    public static final EYBCardData DATA = Register(Lelouch.class).SetSkill(3, CardRarity.RARE, EYBCardTarget.ALL).SetColor(CardColor.COLORLESS).SetSeries(CardSeries.CodeGeass);
    public static final PowerHelper GEASS = new PowerHelper(GeassPower.POWER_ID, null, (o, s, a) -> new GeassPower(o));

    public Lelouch()
    {
        super(DATA);

        Initialize(0, 0, 3);
        SetCostUpgrade(-1);

        SetExhaust(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Top.ExhaustFromHand(name, magicNumber, true).ShowEffect(true, true)
        .SetOptions(true, true, true);

        GameActions.Bottom.VFX(new BorderFlashEffect(Color.RED));
        GameActions.Bottom.SFX("MONSTER_COLLECTOR_DEBUFF");
        GameActions.Bottom.ApplyPower(TargetHelper.Enemies(), GEASS);
    }
}