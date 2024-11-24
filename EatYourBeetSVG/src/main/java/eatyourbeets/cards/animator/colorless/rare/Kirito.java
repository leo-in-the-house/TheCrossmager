package eatyourbeets.cards.animator.colorless.rare;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.ViolentAttackEffect;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardSeries;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Kirito extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Kirito.class)
            .SetAttack(2, CardRarity.RARE)
            .SetColor(CardColor.COLORLESS)
            .SetSeries(CardSeries.SwordArtOnline);

    public Kirito()
    {
        super(DATA);

        Initialize(2, 0);
        SetCostUpgrade(-1);

        SetAffinity_White(1, 0, 1);
        SetAffinity_Green(1, 0, 0);
        SetAffinity_Red(1, 0, 0);
    }

    @Override
    protected float ModifyDamage(AbstractMonster enemy, float amount)
    {
        if (enemy != null)
        {
            amount += GameUtilities.GetIntent(enemy).GetDamage(false);
        }

        return super.ModifyDamage(enemy, amount);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);

        if (damage >= 20)
        {
            //GameActions.Bottom.VFX(new WeightyImpactEffect(m.hb.cX, m.hb.cY));
            //GameActions.Bottom.Wait(0.8f);
            GameActions.Bottom.VFX(new ViolentAttackEffect(m.hb.cX, m.hb.cY, Color.SKY));
            GameActions.Bottom.DealDamage(this, m, AttackEffects.NONE);
        }
        else
        {
            GameActions.Bottom.DealDamage(this, m, AttackEffects.SLASH_VERTICAL);
        }
    }
}