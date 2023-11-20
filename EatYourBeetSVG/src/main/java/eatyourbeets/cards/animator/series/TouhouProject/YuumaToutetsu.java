package eatyourbeets.cards.animator.series.TouhouProject;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class YuumaToutetsu extends AnimatorCard {
    public static final EYBCardData DATA = Register(YuumaToutetsu.class)
            .SetAttack(1, CardRarity.RARE)
            .SetSeriesFromClassPackage();

    public YuumaToutetsu() {
        super(DATA);

        Initialize(4, 0, 1);
        SetUpgrade(4, 0, 0);

        SetAffinity_Violet(1, 0, 1);
        SetAffinity_Red(1, 0, 1);
    }

    @Override
    public AbstractAttribute GetDamageInfo()
    {
        return super.GetDamageInfo().AddMultiplier(magicNumber);
    }

    @Override
    public void triggerWhenDrawn() {
        super.triggerWhenDrawn();

        GameActions.Bottom.ExhaustFromHand(name, 1, false)
            .SetOptions(true, false, false)
            .AddCallback(cards -> {
                for (AbstractCard card : cards) {
                    if (GameUtilities.HasAttackMultiplier(card)) {
                        GameUtilities.IncreaseMagicNumber(this, GameUtilities.GetAttackMultiplier(card), false);
                    }
                    if (GameUtilities.HasBlockMultiplier(card)) {
                        GameUtilities.IncreaseMagicNumber(this, GameUtilities.GetBlockMultiplier(card), false);
                    }
                }
            });
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        for (int i=0; i<magicNumber; i++) {
            GameActions.Bottom.DealDamage(this, m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        }
    }
}