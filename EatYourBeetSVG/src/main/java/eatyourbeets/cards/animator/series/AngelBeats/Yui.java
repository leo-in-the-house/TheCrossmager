package eatyourbeets.cards.animator.series.AngelBeats;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.special.GirlDeMo;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.modifiers.CostModifiers;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.utilities.GameActions;

public class Yui extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Yui.class)
            .SetSkill(2, CardRarity.UNCOMMON, EYBCardTarget.None)
            .SetSeriesFromClassPackage();
    static
    {
        DATA.AddPreview(new GirlDeMo(), true);
    }

    public Yui()
    {
        super(DATA);

        Initialize(0, 0, 1, 2);

        SetAffinity_Yellow(1);
        SetAffinity_White(1);

        SetExhaust(true);
    }

    @Override
    protected void OnUpgrade()
    {
        SetRetain(true);
    }


    @Override
    public void Refresh(AbstractMonster enemy) {
        super.Refresh(enemy);
        RefreshCost();
    }

    public void RefreshCost()
    {
        int numEthereal = 0;

        for (AbstractCard card : player.exhaustPile.group) {
            if (card.isEthereal) {
                numEthereal++;
            }
        }

        if (numEthereal >= 5) {
            CostModifiers.For(this).Add("Yui", -1 * (numEthereal / 5));
        }
        else {
            CostModifiers.For(this).Remove("Yui");
        }
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.Motivate(secondaryValue);

       GameActions.Bottom.MakeCardInDrawPile(new GirlDeMo())
               .SetUpgrade(upgraded, true);
    }
}