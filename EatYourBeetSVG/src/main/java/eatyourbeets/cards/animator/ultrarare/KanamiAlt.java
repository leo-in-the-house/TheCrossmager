package eatyourbeets.cards.animator.ultrarare;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.ViolentAttackEffect;
import eatyourbeets.cards.base.*;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.interfaces.markers.Hidden;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class KanamiAlt extends AnimatorCard_UltraRare implements Hidden
{
    public static final EYBCardData DATA = Register(KanamiAlt.class)
            .SetAttack(1, CardRarity.SPECIAL, EYBAttackType.Normal, EYBCardTarget.ALL)
            .SetColor(CardColor.COLORLESS)
            .SetSeries(CardSeries.LogHorizon);

    public KanamiAlt()
    {
        super(DATA);

        Initialize(999, 0);
        SetCostUpgrade(-1);

        SetAffinity_Star(1);

        SetDelayed(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);

        for (AbstractMonster monster : GameUtilities.GetEnemies(true)) {
            GameActions.Bottom.VFX(new ViolentAttackEffect(monster.hb.cX, monster.hb.cY, Color.RED.cpy()));
            GameActions.Bottom.DealDamage(this, monster, AttackEffects.NONE);
        }
    }
}