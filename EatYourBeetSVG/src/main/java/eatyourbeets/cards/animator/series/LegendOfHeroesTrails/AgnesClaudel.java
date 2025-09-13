package eatyourbeets.cards.animator.series.LegendOfHeroesTrails;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.watcher.SkipEnemiesTurnAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.TimeWarpTurnEndEffect;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.effects.VFX;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class AgnesClaudel extends AnimatorCard {
    public static final EYBCardData DATA = Register(AgnesClaudel.class)
            .SetSkill(0, CardRarity.RARE, EYBCardTarget.None)
            .SetSeriesFromClassPackage();

    public AgnesClaudel() {
        super(DATA);

        Initialize(0, 0, 0);
        SetUpgrade(0, 0, 0);

        SetAffinity_White(1);
        SetAffinity_Teal(1);

        SetExhaust(true);
    }

    @Override
    protected void OnUpgrade()
    {
        super.OnUpgrade();
        SetRetain(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.VFX(new TimeWarpTurnEndEffect());
        GameActions.Bottom.VFX(new BorderFlashEffect(Color.WHITE, true));

        GameActions.Bottom.Add(new SkipEnemiesTurnAction());
        GameActions.Bottom.ApplyPower(new AgnesClaudelPower(p, 1));
    }


    public static class AgnesClaudelPower extends AnimatorPower {
        public AgnesClaudelPower(AbstractCreature owner, int amount) {
            super(owner, AgnesClaudel.DATA);
            Initialize(amount, PowerType.DEBUFF, false);
        }


        public void atStartOfTurn() {
            this.flash();

            if (amount <= 0) {
                GameActions.Bottom.BorderLongFlash(Color.WHITE);
                GameActions.Bottom.VFX(VFX.Cataclysm());
                GameActions.Bottom.Wait(1f);
                GameActions.Bottom.LoseHP(99999999, AbstractGameAction.AttackEffect.NONE);
                RemovePower();
            }
            else {
                amount--;
            }
        }

        @Override
        public void updateDescription() {
            description = FormatDescription(0, amount);
        }
    }
}