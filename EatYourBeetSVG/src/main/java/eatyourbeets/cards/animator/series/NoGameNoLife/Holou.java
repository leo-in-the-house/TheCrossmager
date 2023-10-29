package eatyourbeets.cards.animator.series.NoGameNoLife;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ShuffleAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.cards.base.attributes.TempHPAttribute;
import eatyourbeets.effects.SFX;
import eatyourbeets.effects.VFX;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Holou extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Holou.class)
            .SetAttack(3, CardRarity.RARE, EYBAttackType.Elemental, EYBCardTarget.ALL)
            .SetSeriesFromClassPackage();

    public Holou()
    {
        super(DATA);

        Initialize(7, 0, 7);
        SetUpgrade(8, 0, 8);

        SetAffinity_White(1, 0, 2);
        SetAffinity_Teal(1, 0, 2);
        SetAffinity_Yellow(1, 0, 2);

        SetExhaust(true);
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

        GameActions.Bottom.VFX(VFX.Mindblast(p.dialogX, p.dialogY).SetColor(Color.YELLOW));
        GameActions.Bottom.DealDamageToAll(this, AbstractGameAction.AttackEffect.NONE);
        GameActions.Bottom.GainTemporaryHP(magicNumber);

        GameActions.Bottom.SFX(SFX.POWER_TIME_WARP, 0.5f, 0.5f);
        GameActions.Bottom.MoveCards(p.hand, p.drawPile)
            .SetFilter(card -> card.costForTurn == 0);
        GameActions.Bottom.MoveCards(p.discardPile, p.drawPile)
            .SetFilter(card -> card.costForTurn == 0)
            .ShowEffect(true, false, Math.max(0.0075f, 0.09f - p.drawPile.size() * 0.01f));
        GameActions.Bottom.MoveCards(p.exhaustPile, p.drawPile)
                .SetFilter(card -> card.costForTurn == 0)
                .ShowEffect(true, false, Math.max(0.0075f, 0.09f - p.drawPile.size() * 0.01f));

        GameActions.Bottom.Add(new ShuffleAction(p.drawPile, false));

        GameActions.Bottom.Draw(1);
    }
}