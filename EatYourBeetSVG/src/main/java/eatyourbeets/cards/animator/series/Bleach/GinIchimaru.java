package eatyourbeets.cards.animator.series.Bleach;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.stances.NeutralStance;
import com.megacrit.cardcrawl.vfx.combat.DieDieDieEffect;
import eatyourbeets.cards.animator.special.GinBankai;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.powers.AnimatorClickablePower;
import eatyourbeets.powers.PowerTriggerConditionType;
import eatyourbeets.stances.WrathStance;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameEffects;
import eatyourbeets.utilities.GameUtilities;

public class GinIchimaru extends AnimatorCard {
    public static final EYBCardData DATA = Register(GinIchimaru.class)
            .SetPower(3, CardRarity.RARE)
            .SetSeriesFromClassPackage()
            .PostInitialize(data -> data.AddPreview(new GinBankai(), true));

    public GinIchimaru() {
        super(DATA);

        Initialize(0, 0, 7);
        SetUpgrade(0, 0, 6);

        SetAffinity_Pink(2);
        SetAffinity_Green(1);
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

        GameActions.Bottom.StackPower(new GinIchimaruPower(p, magicNumber, upgraded));
    }

    public static class GinIchimaruPower extends AnimatorClickablePower {

        private boolean upgraded;
        public GinIchimaruPower(AbstractCreature owner, int amount, boolean upgraded) {
            super(owner, GinIchimaru.DATA, PowerTriggerConditionType.Special, 0, GinIchimaru.GinIchimaruPower::CheckCondition, __ -> {});

            this.triggerCondition.SetUses(1, false, true);
            this.upgraded = upgraded;

            Initialize(amount);
        }

        private static boolean CheckCondition(int cost)
        {
            return GameUtilities.InStance(WrathStance.STANCE_ID);
        }

        @Override
        public String GetUpdatedDescription()
        {
            return FormatDescription( upgraded ? 1 : 0, amount);
        }

        @Override
        public void atEndOfTurn(boolean isPlayer) {
            super.atEndOfTurn(isPlayer);

            int[] damageMatrix = DamageInfo.createDamageMatrix(amount, true);
            GameActions.Bottom.DealDamageToAll(damageMatrix, DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.SLASH_DIAGONAL)
                    .SetDamageEffect((c, __) -> {
                        GameEffects.List.Add(new DieDieDieEffect());
                    });
        }

        @Override
        public void OnUse(AbstractMonster m)
        {
            super.OnUse(m);

            if (eatyourbeets.stances.WrathStance.IsActive()) {
                GameActions.Bottom.ChangeStance(NeutralStance.STANCE_ID);

                //Will trigger on every use, including the one that was just activated
                for (int i=0; i<=triggerCondition.uses; i++) {
                    GameActions.Bottom.MakeCardInDrawPile(new GinBankai())
                            .SetUpgrade(upgraded, true);
                }

                RemovePower();
            }
        }
    }
}