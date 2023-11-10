package eatyourbeets.cards.animator.series.Rewrite;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.powers.PowerHelper;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.TargetHelper;

public class LuciaKonohana extends AnimatorCard {
    public static final EYBCardData DATA = Register(LuciaKonohana.class)
            .SetSkill(-1, CardRarity.UNCOMMON, EYBCardTarget.ALL)
            .SetSeriesFromClassPackage();

    public LuciaKonohana() {
        super(DATA);

        Initialize(0, 0, 4);
        SetUpgrade(0, 0, 1);

        SetExhaust(true);

        SetAffinity_White(1);
        SetAffinity_Violet(1);
    }

    @Override
    protected void OnUpgrade() {
        super.OnUpgrade();

        SetExhaust(false);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);
        int stacks = GameUtilities.UseXCostEnergy(this);

        if (stacks > 0) {
            GameActions.Bottom.ApplyPoison(TargetHelper.Enemies(), stacks * magicNumber);

            if (stacks >= 2) {
                for (AbstractMonster enemy : GameUtilities.GetEnemies(true)) {
                    GameActions.Bottom.StackPower(new LuciaKonohanaPower(enemy, 1));
                }
            }
        }
    }

    public static class LuciaKonohanaPower extends AnimatorPower {

        public LuciaKonohanaPower(AbstractCreature owner, int amount) {
            super(owner, LuciaKonohana.DATA);
            this.type = PowerType.DEBUFF;

            Initialize(amount);
        }

        @Override
        public void updateDescription()
        {
            description = FormatDescription(0, amount);
        }

        @Override
        public void onDeath()
        {
            final AbstractCreature corpse = this.owner;
            int powAmount;

            for (AbstractPower debuff : corpse.powers)
            {
                for (PowerHelper commonDebuffHelper : GameUtilities.GetCommonDebuffs()) {
                    if (commonDebuffHelper.ID.equals(debuff.ID)) {
                        powAmount = GameUtilities.GetPowerAmount(corpse, debuff.ID);
                        GameActions.Bottom.StackPower(TargetHelper.Enemies(), commonDebuffHelper, powAmount);
                    }
                }
            }
        }
    }
}