package eatyourbeets.cards.animator.series.Rewrite;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardEffectChoice;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.effects.GenericEffects.GenericEffect_EnterStance;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.stances.*;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class KotarouTennouji extends AnimatorCard {
    public static final EYBCardData DATA = Register(KotarouTennouji.class)
            .SetAttack(3, CardRarity.RARE)
            .SetSeriesFromClassPackage();

    public KotarouTennouji() {
        super(DATA);

        Initialize(3, 5, 2);
        SetUpgrade(2, 1, 0);

        SetAffinity_White(2, 0, 2);
        SetAffinity_Green(2, 0, 2);

        SetUnique(true, true);
        SetDelayed(true);
        SetExhaust(true);
    }

    @Override
    protected void OnUpgrade()
    {
        super.OnUpgrade();

        if (timesUpgraded >= 8) {
            LoadImage("_Upgraded2");
        }
        else if (timesUpgraded >= 2)
        {
            LoadImage("_Upgraded");
        }
    }

    @Override
    public void initializeDescription() {
        super.initializeDescription();

        if (timesUpgraded >= 16) {
            cardText.OverrideDescription(DATA.Strings.EXTENDED_DESCRIPTION[3], true);
        }
        else if (timesUpgraded >= 8) {
            cardText.OverrideDescription(DATA.Strings.EXTENDED_DESCRIPTION[2], true);
        }
        else if (timesUpgraded >= 4) {
            cardText.OverrideDescription(DATA.Strings.EXTENDED_DESCRIPTION[1], true);
        }
        else if (timesUpgraded >= 2) {
            cardText.OverrideDescription(DATA.Strings.EXTENDED_DESCRIPTION[0], true);
        }
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.DealDamage(this, m, AttackEffects.CLAW);

        if (this.timesUpgraded >= 2) {
            CardEffectChoice choices = new CardEffectChoice();

            if (choices.TryInitialize(this)) {
                choices.AddEffect(new GenericEffect_EnterStance(CalmStance.STANCE_ID));
                choices.AddEffect(new GenericEffect_EnterStance(WrathStance.STANCE_ID));

                if (this.timesUpgraded >= 4) {
                    choices.AddEffect(new GenericEffect_EnterStance(MagicStance.STANCE_ID));
                }

                if (this.timesUpgraded >= 8) {
                    choices.AddEffect(new GenericEffect_EnterStance(TranceStance.STANCE_ID));
                }

                if (this.timesUpgraded >= 16) {
                    choices.AddEffect(new GenericEffect_EnterStance(DivinityStance.STANCE_ID));
                }

                choices.Initialize(this);
            }

            choices.Select(1, m);
        }
        else {
            GameActions.Bottom.ChangeStance(CalmStance.STANCE_ID);
        }

        if (CheckSpecialConditionLimited(false)) {
            GameActions.Bottom.ModifyAllInstances(uuid, AbstractCard::upgrade)
                    .IncludeMasterDeck(true)
                    .IsCancellable(false);
        }
    }
}