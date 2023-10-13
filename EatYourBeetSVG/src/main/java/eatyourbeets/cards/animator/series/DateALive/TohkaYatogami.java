package eatyourbeets.cards.animator.series.DateALive;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.curse.common.Curse_Depression;
import eatyourbeets.cards.animator.special.InverseTohka;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBAttackType;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.modifiers.CostModifiers;
import eatyourbeets.ui.common.EYBCardPopupActions;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class TohkaYatogami extends AnimatorCard
{
    public static final EYBCardData DATA = Register(TohkaYatogami.class)
            .SetAttack(2, CardRarity.UNCOMMON, EYBAttackType.Normal)
            .SetSeriesFromClassPackage()
            .PostInitialize(data ->
            {
                data.AddPopupAction(new EYBCardPopupActions.DAL_Inversion(InverseTohka.DATA));
                data.AddPreview(new InverseTohka(), true);
                data.AddPreview(new Curse_Depression(), false);
            });

    private boolean transformed;

    public TohkaYatogami()
    {
        super(DATA);

        Initialize(10, 0, 2, 10);

        SetAffinity_Black(1, 0, 1);
        SetAffinity_Brown(1);
    }

    @Override
    protected void OnUpgrade()
    {
        SetRetain(true);
        SetHaste(true);
    }

    @Override
    public void Refresh(AbstractMonster enemy) {
        super.Refresh(enemy);
        RefreshCost();
    }

    public void RefreshCost()
    {
        if (player.currentBlock >= 10) {
            CostModifiers.For(this).Add("Tohka", -1 * magicNumber);
        }
        else {
            CostModifiers.For(this).Remove("Tohka");
        }
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.DealDamage(this, m, AbstractGameAction.AttackEffect.SLASH_VERTICAL);
    }
}