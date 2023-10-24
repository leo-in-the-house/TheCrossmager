package eatyourbeets.cards.animator.series.MadokaMagica;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.curse.special.Curse_GriefSeed;
import eatyourbeets.cards.base.*;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.cards.base.attributes.TempHPAttribute;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.effects.SFX;
import eatyourbeets.effects.VFX;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameEffects;
import eatyourbeets.utilities.GameUtilities;

public class IrohaTamaki extends AnimatorCard
{
    public static final EYBCardData DATA = Register(IrohaTamaki.class)
            .SetAttack(1, CardRarity.COMMON, EYBAttackType.Ranged, EYBCardTarget.Random)
            .SetSeriesFromClassPackage();

    public IrohaTamaki()
    {
        super(DATA);

        Initialize(4, 0, 5);
        SetUpgrade(2, 0, 2);

        SetAffinity_White(1);
    }

    @Override
    public AbstractAttribute GetSpecialInfo()
    {
        return TempHPAttribute.Instance.SetCard(this, true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.SFX(SFX.ANIMATOR_ARROW);
        GameActions.Bottom.DealDamageToRandomEnemy(this, AttackEffects.NONE)
                .SetDamageEffect(c -> GameEffects.List.Add(VFX.ThrowDagger(c.hb, 0.3f).SetColor(Color.TAN)).duration * 0.1f)
                .SetDuration(0.05f, false);

        GameActions.Bottom.GainTemporaryHP(magicNumber);

        GameActions.Bottom.MakeCardInHand(new Curse_GriefSeed())
            .AddCallback(GameActions.Top::Exhaust);
    }

    @Override
    public boolean CheckSpecialCondition(boolean tryUse)
    {
        for (AbstractCard c : player.drawPile.group)
        {
            if (GameUtilities.IsHindrance(c) && !c.hasTag(HASTE))
            {
                return super.CheckSpecialCondition(tryUse);
            }
        }

        return false;
    }
}