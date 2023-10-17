package eatyourbeets.cards.animator.series.Konosuba;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.tempCards.Miracle;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.Dark;
import com.megacrit.cardcrawl.orbs.Frost;
import com.megacrit.cardcrawl.orbs.Lightning;
import com.megacrit.cardcrawl.powers.AbstractPower;
import eatyourbeets.cards.animator.curse.common.Curse_Greed;
import eatyourbeets.cards.animator.special.Darkness_Adrenaline;
import eatyourbeets.cards.base.Affinity;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.effects.SFX;
import eatyourbeets.effects.VFX;
import eatyourbeets.powers.AnimatorClickablePower;
import eatyourbeets.powers.PowerTriggerConditionType;
import eatyourbeets.utilities.CardSelection;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.TargetHelper;

public class Destroyer extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Destroyer.class)
            .SetPower(4, CardRarity.RARE)
            .SetSeriesFromClassPackage();

    public Destroyer()
    {
        super(DATA);

        Initialize(0, 0, 3, 20);
        SetCostUpgrade(-1);

        SetAffinity_Teal(2);
        SetAffinity_Violet(2);
    }

    @Override
    protected void OnUpgrade()
    {
        SetRetainOnce(true);
    }
    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.StackPower(new DestroyerPower(p, magicNumber,1, secondaryValue));
    }

    public static class DestroyerPower extends AnimatorClickablePower
    {
        private int damage;

        public DestroyerPower(AbstractCreature owner, int amount, int damage, int uses)
        {
            super(owner, Destroyer.DATA, PowerTriggerConditionType.Energy, 1);

            this.triggerCondition.SetUses(uses, true, true);

            this.damage = damage;
            Initialize(amount, PowerType.BUFF, true);
        }

        @Override
        public String GetUpdatedDescription()
        {
            return FormatDescription(0, damage);
        }

        @Override
        protected void OnSamePowerApplied(AbstractPower power)
        {
            super.OnSamePowerApplied(power);
        }

        @Override
        public void playApplyPowerSfx()
        {
            SFX.Play(SFX.ORB_PLASMA_CHANNEL, 0.7f);
        }

        @Override
        public void atEndOfTurnPreEndTurnCards(boolean isPlayer)
        {
            super.atEndOfTurnPreEndTurnCards(isPlayer);

            final int[] damageMatrix = DamageInfo.createDamageMatrix(damage, true, false);
            GameActions.Bottom.SFX(SFX.ATTACK_DEFECT_BEAM, 0.65f, 0.7f);
            GameActions.Bottom.VFX(VFX.SweepingBeam(owner.hb, VFX.FlipHorizontally(), new Color(1f, 0, 0f, 1f)), 0.3f);
            GameActions.Bottom.DealDamageToAll(damageMatrix, DamageInfo.DamageType.THORNS, AttackEffects.FIRE)
            .SetDamageEffect((c, __) -> TriggerRandomDestroyerEffects());

            flashWithoutSound();
            ReducePower(1);
        }

        @Override
        public void OnUse(AbstractMonster m)
        {
            playApplyPowerSfx();
            IncreasePower(1);
        }

        @Override
        public AbstractPower makeCopy()
        {
            return new DestroyerPower(owner, amount, triggerCondition.baseUses, damage);
        }

        private void TriggerRandomDestroyerEffects() {
            int times = rng.random(1, 2);

            for (int i=0; i<times; i++) {
                TriggerRandomDestroyerEffect();
            }
        }

        private void TriggerRandomDestroyerEffect() {
            flash();
            switch (rng.random(31)) {
                case 0:
                    GameActions.Top.ApplyWeak(TargetHelper.Enemies(), 3);
                    break;
                case 1:
                    GameActions.Top.ApplyVulnerable(TargetHelper.Enemies(), 5);
                    break;
                case 2:
                    GameActions.Top.ApplyPoison(TargetHelper.Enemies(), 4);
                    break;
                case 3:
                    GameActions.Top.ApplyBurning(TargetHelper.Enemies(), 6);
                    break;
                case 4:
                    for (AbstractMonster enemy : GameUtilities.GetEnemies(true)) {
                        GameActions.Top.ReduceStrength(enemy, 8, true);
                    }
                    break;
                case 5:
                    GameActions.Top.ApplyFreezing(TargetHelper.Enemies(), 4);
                    break;
                case 6:
                    GameActions.Top.GainBlur(2);
                    break;
                case 7:
                    GameActions.Top.GainVigor(8);
                    break;
                case 8:
                    GameActions.Top.ChannelRandomOrb(1);
                    break;
                case 9:
                    GameActions.Top.ChannelOrbs(Lightning::new,2);
                    break;
                case 10:
                    GameActions.Top.ChannelOrbs(Frost::new,2);
                    break;
                case 11:
                    GameActions.Top.ChannelOrbs(Dark::new,2);
                    break;
                case 12:
                    GameActions.Top.GainPlatedArmor(3);
                    break;
                case 13:
                    GameActions.Top.GainMetallicize(2);
                    break;
                case 14:
                    GameActions.Top.GainBlock(12);
                    break;
                case 15:
                    GameActions.Top.GainTemporaryHP(6);
                    break;
                case 16:
                    GameActions.Top.GainEnergyNextTurn(1);
                    break;
                case 17:
                    GameActions.Top.DrawNextTurn(2);
                    break;
                case 18:
                    GameActions.Top.GainDuplication(1);
                    break;
                case 19:
                    GameActions.Top.GainFlameBarrier(4);
                    break;
                case 20:
                    GameActions.Top.GainAffinity(Affinity.Star, 1);
                    break;
                case 21:
                    GameActions.Top.GainAffinity(Affinity.Sealed, 1);
                    break;
                case 22:
                    GameActions.Top.GainRandomAffinityPower(4, false);
                    break;
                case 23:
                    GameActions.Top.GainRandomAffinityPower(3, true);
                    break;
                case 24:
                    final int[] damageMatrix = DamageInfo.createDamageMatrix(damage, true, false);
                    GameActions.Top.MakeCardInDiscardPile(new Curse_Greed());
                    GameActions.Top.DealDamageToAll(damageMatrix, DamageInfo.DamageType.THORNS, AttackEffects.FIRE);
                    GameActions.Top.VFX(VFX.SweepingBeam(owner.hb, VFX.FlipHorizontally(), new Color(1f, 0, 0f, 1f)), 0.3f);
                    GameActions.Top.SFX(SFX.ATTACK_DEFECT_BEAM, 0.65f, 0.7f);
                    break;
                case 25:
                    GameActions.Top.MakeCardInDrawPile(AbstractDungeon.getCard(CardRarity.UNCOMMON, rng).makeCopy())
                        .SetDestination(CardSelection.Top);
                    break;
                case 26:
                    GameActions.Top.MakeCardInDrawPile(new Miracle())
                            .SetDestination(CardSelection.Top);
                    break;
                case 27:
                    GameActions.Top.MakeCardInDrawPile(new Darkness_Adrenaline())
                            .SetDestination(CardSelection.Top);
                    break;
                case 28:
                    GameActions.Top.GainArtifact(1);
                    break;
                case 29:
                    GameActions.Top.GainThorns(1);
                    break;
                case 30:
                    damage += 15;
                    updateDescription();
                    break;
                default:
                    IncreasePower(1);
                    break;
            }
        }
    }
}