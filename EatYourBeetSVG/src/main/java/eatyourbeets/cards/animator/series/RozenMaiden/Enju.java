package eatyourbeets.cards.animator.series.RozenMaiden;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.interfaces.listeners.OnAddToDeckListener;
import eatyourbeets.powers.AnimatorClickablePower;
import eatyourbeets.powers.PowerTriggerConditionType;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.RandomizedList;

import java.util.LinkedList;
import java.util.List;

public class Enju extends AnimatorCard implements OnAddToDeckListener{
    public static final EYBCardData DATA = Register(Enju.class)
            .SetPower(3, CardRarity.RARE)
            .SetSeriesFromClassPackage()
            .PostInitialize(data ->
            {
                data.AddPreview(new Suigintou(), true);
                data.AddPreview(new Kanaria(), true);
                data.AddPreview(new Souseiseki(), true);
                data.AddPreview(new Suiseiseki(), true);
                data.AddPreview(new Shinku(), true);
                data.AddPreview(new Hinaichigo(), true);
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

        private static final List<String> ROZEN_MAIDEN_DOLLS = new LinkedList<String>();

        private boolean upgraded;
        public EnjuPower(AbstractCreature owner, int amount, boolean upgraded) {
            super(owner, Enju.DATA, PowerTriggerConditionType.Energy, 2);

            triggerCondition.SetUses(1, true, true);

            this.upgraded = upgraded;

            if (ROZEN_MAIDEN_DOLLS.isEmpty()) {
                ROZEN_MAIDEN_DOLLS.add(Suigintou.DATA.ID);
                ROZEN_MAIDEN_DOLLS.add(Kanaria.DATA.ID);
                ROZEN_MAIDEN_DOLLS.add(Souseiseki.DATA.ID);
                ROZEN_MAIDEN_DOLLS.add(Suiseiseki.DATA.ID);
                ROZEN_MAIDEN_DOLLS.add(Shinku.DATA.ID);
                ROZEN_MAIDEN_DOLLS.add(Hinaichigo.DATA.ID);
                ROZEN_MAIDEN_DOLLS.add(Barasuishou.DATA.ID);
            }

            Initialize(amount);
            updateDescription();
        }

        @Override
        public void OnUse(AbstractMonster m) {
            super.OnUse(m);

            RandomizedList<AbstractCard> possibleRozenMaidenDolls = new RandomizedList<>();

            for (String dollID : ROZEN_MAIDEN_DOLLS) {
                if (UnlockTracker.isCardSeen(dollID)) {
                    possibleRozenMaidenDolls.Add(GetClassCard(dollID, upgraded));
                }
            }

            if (possibleRozenMaidenDolls.Size() > 0) {
                for (int i=0; i<amount; i++) {
                    GameActions.Bottom.MakeCardInHand(possibleRozenMaidenDolls.Retrieve(rng, false).makeCopy())
                            .AddCallback(GameUtilities::Retain);
                }
            }
        }

        @Override
        public String GetUpdatedDescription()
        {
            return FormatDescription(upgraded ? 1 : 0, amount);
        }
    }
}