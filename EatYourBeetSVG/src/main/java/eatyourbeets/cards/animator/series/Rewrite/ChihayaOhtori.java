package eatyourbeets.cards.animator.series.Rewrite;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.stances.AbstractStance;
import eatyourbeets.cards.animator.special.SakuyaOhtori;
import eatyourbeets.cards.base.*;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.cards.base.modifiers.CostModifiers;
import eatyourbeets.interfaces.subscribers.OnEndOfTurnLastSubscriber;
import eatyourbeets.interfaces.subscribers.OnStanceChangedSubscriber;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.ui.common.EYBCardPopupActions;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class ChihayaOhtori extends AnimatorCard implements OnStanceChangedSubscriber, OnEndOfTurnLastSubscriber {
    public static final EYBCardData DATA = Register(ChihayaOhtori.class)
            .SetAttack(2, CardRarity.UNCOMMON, EYBAttackType.Normal, EYBCardTarget.Random)
            .SetSeriesFromClassPackage()
            .PostInitialize(data ->
            {
                data.AddPopupAction(new EYBCardPopupActions.Rewrite_Sakuya(SakuyaOhtori.DATA));
                data.AddPreview(new SakuyaOhtori(), false);
            });

    public ChihayaOhtori() {
        super(DATA);

        Initialize(8, 0, 3);
        SetUpgrade(0, 0, 1);

        SetAffinity_Red(2, 0, 2);
    }

    @Override
    public AbstractAttribute GetDamageInfo()
    {
        return super.GetDamageInfo().AddMultiplier(magicNumber);
    }

    @Override
    public void OnEndOfTurnLast(boolean isPlayer)
    {
        RefreshCost();
    }

    @Override
    public void OnStanceChanged(AbstractStance oldStance, AbstractStance newStance) {
        RefreshCost();
    }

    @Override
    public void Refresh(AbstractMonster enemy)
    {
        super.Refresh(enemy);

        RefreshCost();
    }

    private void RefreshCost()
    {
        if (GameUtilities.InGame() && player != null && player.hand != null) {
            CostModifiers.For(this).Set(-CombatStats.StanceChangesThisTurn());
        }
    }
    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        for (int i=0; i<magicNumber; i++) {
            GameActions.Bottom.DealDamageToRandomEnemy(this, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        }

        for (AbstractCard card : player.hand.group) {
            if (card.type != CardType.ATTACK) {
                GameActions.Bottom.Discard(card, player.hand);
            }
        }
    }

    @Override
    public void triggerWhenCreated(boolean startOfBattle)
    {
        super.triggerWhenCreated(startOfBattle);

        CombatStats.onStanceChanged.Subscribe(this);
        CombatStats.onEndOfTurnLast.Subscribe(this);
    }
}