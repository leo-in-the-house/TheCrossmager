package eatyourbeets.cards.animator.series.Atelier;

import com.megacrit.cardcrawl.actions.common.ObtainPotionAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.special.Lulua;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.powers.AnimatorClickablePower;
import eatyourbeets.powers.PowerTriggerConditionType;
import eatyourbeets.ui.common.EYBCardPopupActions;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Rorona extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Rorona.class)
            .SetPower(2, CardRarity.UNCOMMON)
            .SetSeriesFromClassPackage()
            .PostInitialize(data ->
            {
                data.AddPopupAction(new EYBCardPopupActions.Atelier_Rorona(2, Lulua.DATA));
                data.AddPreview(new Lulua(), true);
            });

    public Rorona()
    {
        super(DATA);

        Initialize(0, 0, 30, 1);
        SetUpgrade(0, 0, 0, 1);

        SetAffinity_White(2);
        SetAffinity_Pink(1);

        SetObtainableInCombat(false);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.StackPower(new RoronaPower(p, magicNumber, secondaryValue));
    }

    public static class RoronaPower extends AnimatorClickablePower
    {
        public RoronaPower(AbstractCreature owner, int cost, int maxUses)
        {
            super(owner, Rorona.DATA, PowerTriggerConditionType.Gold, cost);

            this.triggerCondition.SetUses(maxUses, false, true);
            canBeZero = true;
            Initialize(maxUses);
        }

        @Override
        public String GetUpdatedDescription()
        {
            return FormatDescription(0);
        }

        @Override
        public void OnUse(AbstractMonster m)
        {
            super.OnUse(m);

            GameActions.Bottom.Add(new ObtainPotionAction(AbstractDungeon.returnRandomPotion(false)));
        }
    }
}