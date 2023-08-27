package eatyourbeets.cards.animator.series.DateALive;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.special.InverseTohka;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBAttackType;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.modifiers.CostModifiers;
import eatyourbeets.interfaces.subscribers.OnBlockGainedSubscriber;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class TohkaYatogami extends AnimatorCard implements OnBlockGainedSubscriber
{
    public static final EYBCardData DATA = Register(TohkaYatogami.class)
            .SetAttack(2, CardRarity.UNCOMMON, EYBAttackType.Normal)
            .SetSeriesFromClassPackage();

    private boolean transformed;

    static
    {
        DATA.AddPreview(new InverseTohka(), true);
    }

    public TohkaYatogami()
    {
        super(DATA);

        Initialize(10, 0, 2, 10);

        SetAffinity_Light(1);
    }

    @Override
    protected void OnUpgrade()
    {
        SetRetain(true);
    }

    @Override
    public void OnBlockGained(AbstractCreature creature, int block) {
        RefreshCost();
    }

    public void RefreshCost()
    {
        if (player.currentBlock >= 10) {
            CostModifiers.For(this).Set(cardData.BaseCost - magicNumber);
        }
        else {
            CostModifiers.For(this).Set(cardData.BaseCost);
        }
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.DealDamage(this, m, AbstractGameAction.AttackEffect.SLASH_VERTICAL);
    }
}