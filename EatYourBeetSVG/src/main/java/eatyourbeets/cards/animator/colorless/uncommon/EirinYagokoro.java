package eatyourbeets.cards.animator.colorless.uncommon;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.Sozu;
import eatyourbeets.cards.base.*;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.interfaces.listeners.OnAddToDeckListener;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class EirinYagokoro extends AnimatorCard implements OnAddToDeckListener
{
    public static final EYBCardData DATA = Register(EirinYagokoro.class)
            .SetAttack(2, CardRarity.UNCOMMON, EYBAttackType.Ranged, EYBCardTarget.Normal)
            
            .SetColor(CardColor.COLORLESS)
            .SetSeries(CardSeries.TouhouProject);

    public EirinYagokoro()
    {
        super(DATA);

        Initialize(8, 0, 2);
        SetUpgrade(0, 0, 1);

        SetAffinity_Teal(2, 0, 2);
    }

    @Override
    public AbstractAttribute GetDamageInfo()
    {
        return super.GetDamageInfo().AddMultiplier(magicNumber);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.DealDamage(this, m, AttackEffects.BLUNT_HEAVY)
        .AddCallback(m.currentBlock, (initialBlock, target) ->
        {
            if (GameUtilities.IsDeadOrEscaped(target) || (initialBlock > 0 && target.currentBlock <= 0))
            {
                CreatePotion();
                GameActions.Last.Exhaust(this);
            }
        });
    }

    public void CreatePotion()
    {
        final AbstractRelic sozu = GameUtilities.GetRelic(Sozu.ID);
        if (sozu == null)
        {
            AbstractDungeon.player.obtainPotion(AbstractDungeon.returnRandomPotion(false));
        }
        else
        {
            sozu.flash();
        }
    }
}