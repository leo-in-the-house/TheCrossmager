package eatyourbeets.cards.animator.series.RozenMaiden;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.interfaces.listeners.OnAddToDeckListener;
import eatyourbeets.powers.AnimatorClickablePower;
import eatyourbeets.powers.PowerTriggerConditionType;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameEffects;
import eatyourbeets.utilities.GameUtilities;

public class Enju extends AnimatorCard implements OnAddToDeckListener{
    public static final EYBCardData DATA = Register(Enju.class)
            .SetPower(4, CardRarity.RARE)
            .SetSeriesFromClassPackage()
            .PostInitialize(data ->
            {
                data.AddPreview(new Barasuishou(), true);
            });

    public Enju() {
        super(DATA);

        Initialize(0, 0, 0);
        SetCostUpgrade(-1);

        SetAffinity_Teal(2);
        SetAffinity_Yellow(2);

        SetObtainableInCombat(false);

        SetDelayed(true);
    }

    @Override
    protected void OnUpgrade()
    {
        super.OnUpgrade();

        SetDelayed(false);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.StackPower(new EnjuPower(p, 1, upgraded));
    }

    public static class EnjuPower extends AnimatorClickablePower {

        private boolean upgraded;
        public EnjuPower(AbstractCreature owner, int amount, boolean upgraded) {
            super(owner, Enju.DATA, PowerTriggerConditionType.Energy, 4);

            triggerCondition.SetUses(1, false, true);

            this.upgraded = upgraded;

            Initialize(amount);
            updateDescription();
        }

        @Override
        public void OnUse(AbstractMonster m) {
            super.OnUse(m);

            AbstractCard barasuishou = new Barasuishou();

            if (upgraded) {
                barasuishou.upgrade();
            }

            GameEffects.TopLevelQueue.ShowAndObtain(barasuishou);
        }

        @Override
        public String GetUpdatedDescription()
        {
            return FormatDescription(upgraded ? 1 : 0, amount);
        }
    }
}