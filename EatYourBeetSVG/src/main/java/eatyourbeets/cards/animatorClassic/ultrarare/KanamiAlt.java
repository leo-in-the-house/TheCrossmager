package eatyourbeets.cards.animatorClassic.ultrarare;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.vfx.combat.ViolentAttackEffect;
import eatyourbeets.cards.base.*;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.interfaces.markers.Hidden;
import eatyourbeets.utilities.GameActions;

public class KanamiAlt extends AnimatorClassicCard_UltraRare implements Hidden
{
    public static final EYBCardData DATA = Register(KanamiAlt.class).SetAttack(2, CardRarity.SPECIAL, EYBAttackType.Normal).SetColor(CardColor.COLORLESS);

    public KanamiAlt()
    {
        super(DATA);

        Initialize(20, 2, 10);
        SetUpgrade(7, 0, 0);

        SetScaling(0, 1, 1);
        
        this.series = CardSeries.LogHorizon;
    }

    @Override
    protected float ModifyBlock(AbstractMonster enemy, float amount)
    {
        if (enemy != null && enemy.hasPower(VulnerablePower.POWER_ID))
        {
            amount += magicNumber;
        }

        return super.ModifyBlock(enemy, amount);
    }

    @Override
    protected float ModifyDamage(AbstractMonster enemy, float amount)
    {
        if (enemy != null)
        {
            if (enemy.type == AbstractMonster.EnemyType.ELITE)
            {
                amount *= 2;
            }
            if (enemy.type == AbstractMonster.EnemyType.BOSS)
            {
                amount *= 3;
            }
        }

        return super.ModifyDamage(enemy, amount);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.VFX(new ViolentAttackEffect(m.hb.cX, m.hb.cY, Color.RED.cpy()));
        GameActions.Bottom.DealDamage(this, m, AttackEffects.NONE)
        .AddCallback(block, (amount, __) ->
        {
            if (amount > 0)
            {
                GameActions.Bottom.GainRed(1);
                GameActions.Bottom.GainGreen(1);
                GameActions.Bottom.GainBlock(amount);
            }
        });
    }
}