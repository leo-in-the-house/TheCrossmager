package eatyourbeets.cards.animator.special;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.ViolentAttackEffect;
import eatyourbeets.actions.basic.RemoveBlock;
import eatyourbeets.cards.animator.series.Katanagatari.Shichika;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Shichika_Kyotouryuu extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Shichika_Kyotouryuu.class)
            .SetAttack(1, CardRarity.SPECIAL)
            .SetSeries(Shichika.DATA.Series);

    public Shichika_Kyotouryuu()
    {
        super(DATA);

        Initialize(1, 0, 1);
        SetUpgrade(0, 0, 0);

        SetAffinity_Red(1, 1, 1);
        SetAffinity_Green(1, 1, 1);

        SetExhaust(true);
        SetEthereal(true);
    }
    @Override
    public AbstractAttribute GetDamageInfo()
    {
        return super.GetDamageInfo().AddMultiplier(magicNumber);
    }

    @Override
    public void Refresh(AbstractMonster enemy)
    {
        super.Refresh(enemy);

        int numSealedCards = 0;

        for (AbstractCard card : player.discardPile.group)
        {
            if (GameUtilities.IsSealed(card)) {
                numSealedCards++;
            }
        }

        GameUtilities.IncreaseMagicNumber(this, 1 + numSealedCards, true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.Add(new RemoveBlock(m, p)).SetVFX(true, true);
        GameActions.Bottom.VFX(new ViolentAttackEffect(m.hb.cX, m.hb.cY, Color.SCARLET.cpy()));

        for (int i=0; i<magicNumber; i++) {
            AbstractGameAction.AttackEffect attackEffect = AttackEffects.BLUNT_LIGHT;

            if (i % 2 == 1) {
                attackEffect = AttackEffects.PUNCH;
            }

            if (i == magicNumber - 1) {
                attackEffect = AttackEffects.BLUNT_HEAVY;
            }

            GameActions.Bottom.DealDamage(this, m, attackEffect);
        }

        GameActions.Bottom.Add(new RemoveBlock(m, p)).SetVFX(true, false);
    }
}