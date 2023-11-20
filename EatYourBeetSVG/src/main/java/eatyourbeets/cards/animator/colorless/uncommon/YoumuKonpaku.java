package eatyourbeets.cards.animator.colorless.uncommon;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardSeries;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.effects.VFX;
import eatyourbeets.interfaces.subscribers.OnStartOfTurnSubscriber;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.stances.AgilityStance;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameEffects;

public class YoumuKonpaku extends AnimatorCard implements OnStartOfTurnSubscriber
{
    public static final EYBCardData DATA = Register(YoumuKonpaku.class)
            .SetAttack(1, CardRarity.UNCOMMON)
            .SetColor(CardColor.COLORLESS)
            .SetSeries(CardSeries.TouhouProject);

    public YoumuKonpaku()
    {
        super(DATA);

        Initialize(3, 3, 2);
        SetUpgrade(1, 2);

        SetAffinity_Black(1, 0, 1);
        SetAffinity_Green(1);

        SetInnate(true);
    }

    @Override
    public AbstractAttribute GetDamageInfo()
    {
        return super.GetDamageInfo().AddMultiplier(magicNumber);
    }

    @Override
    protected float ModifyDamage(AbstractMonster enemy, float amount)
    {
        if (enemy != null && enemy.currentHealth >= enemy.maxHealth)
        {
            amount *= 2;
        }

        return super.ModifyDamage(enemy, amount);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.GainBlock(block);

        for (int i=0; i<magicNumber; i++) {
            GameActions.Bottom.DealDamage(this, m, AttackEffects.NONE)
            .SetDamageEffect(c -> GameEffects.List.Add(VFX.Clash(c.hb)).duration)
            .SetVFXColor(Color.TEAL);
        }
    }
}