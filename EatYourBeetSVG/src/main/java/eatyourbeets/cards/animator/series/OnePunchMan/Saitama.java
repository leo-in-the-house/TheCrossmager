package eatyourbeets.cards.animator.series.OnePunchMan;

import com.evacipated.cardcrawl.mod.stslib.powers.StunMonsterPower;
import com.megacrit.cardcrawl.actions.common.PummelDamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import eatyourbeets.stances.WrathStance;
import eatyourbeets.utilities.GameUtilities;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BufferPower;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;
import com.megacrit.cardcrawl.powers.IntangiblePower;
import com.megacrit.cardcrawl.powers.InvinciblePower;
import eatyourbeets.cards.base.*;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.effects.VFX;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.JUtils;

public class Saitama extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Saitama.class)
            .SetSkill(0, CardRarity.RARE, EYBCardTarget.None)
            .SetSeriesFromClassPackage()
            .PostInitialize(data ->
            {
                data.AddPreview(new Saitama(1), true);
                data.AddPreview(new Saitama(2), true);
                data.AddPreview(new Saitama(3), true);
                data.AddPreview(new Saitama(4), true);
                data.AddPreview(new Saitama(5), true);
            });

    private int stage;

    public Saitama()
    {
        this(0);
    }

    private Saitama(int stage)
    {
        super(DATA);

        Initialize(0, 0);

        SetAffinity_Red(2);
        SetAffinity_Green(2);
        SetAffinity_White(1);

        SetAttackType(EYBAttackType.Normal);
        GameUtilities.ModifyCostForCombat(this, upgraded ? Math.max(0, stage-1) : stage, false);
        this.stage = this.misc = stage;
        SetEffect(stage);
    }

    @Override
    protected void OnUpgrade()
    {
        super.OnUpgrade();

        SetInnate(true);
    }

    @Override
    public AbstractAttribute GetDamageInfo()
    {
        final AbstractAttribute damage = super.GetDamageInfo();
        if (damage != null && stage == 4)
        {
            damage.AddMultiplier(magicNumber);
        }

        return damage;
    }

    @Override
    protected void Refresh(AbstractMonster enemy)
    {
        super.Refresh(enemy);

        if (stage != misc)
        {
            stage = misc = Math.max(Math.min(misc, 5), 0);
            SetEffect(stage);
        }
    }

    @Override
    public AbstractCard makeStatEquivalentCopy()
    {
        AbstractCard card = super.makeStatEquivalentCopy();

        Saitama other = JUtils.SafeCast(card, Saitama.class);
        if (other != null)
        {
            other.misc = other.stage = this.misc;
            other.SetEffect(stage);
        }

        return card;
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        switch (stage)
        {
            case 0:
            {
                // Do Nothing
                break;
            }

            case 1:
            {
                // Draw !M! Cards. NL Gain !SV! Agility.
                GameActions.Bottom.Draw(magicNumber);
                GameActions.Bottom.GainGreen(secondaryValue);

                break;
            }

            case 2:
            {
                // Prevent the next time you would lose HP
                GameActions.Bottom.StackPower(new BufferPower(p, 1));

                break;
            }

            case 3:
            {
                // Gain !M! Force. Gain !B! Block.
                GameActions.Bottom.GainRed(magicNumber);
                GameActions.Bottom.GainBlock(block);

                break;
            }

            case 4:
            {
                // Deal !D! damage !M! times and enter <WS>.
                for (int i = 1; i < magicNumber; i++)
                {
                    GameActions.Bottom.Add(new PummelDamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn)));
                }
                GameActions.Bottom.DealDamage(this, m, AttackEffects.BLUNT_HEAVY);
                GameActions.Bottom.ChangeStance(WrathStance.STANCE_ID);

                break;
            }

            case 5:
            {
                // Remove Intangible. Deal !D! damage. Stun The Enemy
                GameActions.Bottom.RemovePower(p, m, IntangiblePower.POWER_ID);
                GameActions.Bottom.RemovePower(p, m, IntangiblePlayerPower.POWER_ID);
                GameActions.Bottom.RemovePower(p, m, InvinciblePower.POWER_ID);

                GameActions.Bottom.VFX(VFX.VerticalImpact(m.hb));
                GameActions.Bottom.DealDamage(this, m, AttackEffects.PUNCH).SetPiercing(true, true);
                GameActions.Bottom.ShakeScreen(0.5f, ScreenShake.ShakeDur.MED, ScreenShake.ShakeIntensity.MED);

                GameActions.Bottom.ApplyPower(p, m, new StunMonsterPower(m, 1));

                break;
            }
        }

        GameActions.Bottom.ModifyAllInstances(uuid, c ->
        {
            if (c.misc < 5)
            {
                GameUtilities.ModifyCostForCombat(c, 1, true);
                c.misc += 1;
                c.applyPowers();
            }
        });
    }

    private void SetEffect(int stage)
    {
        switch (stage)
        {
            case 0:
            {
                // Do Nothing
                this.cardText.OverrideDescription(null, true);

                Initialize(0, 0, 0, 0);

                this.target = CardTarget.NONE;
                this.type = CardType.SKILL;

                LoadImage(null);

                SetHaste(true);

                break;
            }

            case 1:
            {
                // Draw !M! Cards. NL Gain !SV! Agility.
                this.cardText.OverrideDescription(cardData.Strings.EXTENDED_DESCRIPTION[0], true);

                Initialize(0, 0, 3, 2);

                this.target = CardTarget.SELF;
                this.type = CardType.SKILL;

                LoadImage("_0");

                break;
            }

            case 2:
            {
                // Prevent the next time you would lose HP
                this.cardText.OverrideDescription(cardData.Strings.EXTENDED_DESCRIPTION[1], true);

                Initialize(0, 0, 0, 0);

                this.target = CardTarget.SELF;
                this.type = CardType.SKILL;

                LoadImage("_1");

                break;
            }

            case 3:
            {
                // Gain !M! Force. Gain !B! Block
                this.cardText.OverrideDescription(cardData.Strings.EXTENDED_DESCRIPTION[2], true);

                Initialize(0, 9, 6, 0);

                this.target = CardTarget.SELF;
                this.type = CardType.SKILL;

                AddScaling(Affinity.White, 3);

                LoadImage("_2");

                break;
            }

            case 4:
            {
                // Deal !D! damage !M! times.
                this.cardText.OverrideDescription(cardData.Strings.EXTENDED_DESCRIPTION[3], true);

                Initialize(6, 0, 8, 0);
                
                AddScaling(Affinity.Red, 1);

                this.attackType = EYBAttackType.Normal;
                this.target = CardTarget.ENEMY;
                this.type = CardType.ATTACK;

                LoadImage("_3");

                break;
            }

            case 5:
            {
                // Remove Intangible. Stun the enemy.
                this.cardText.OverrideDescription(cardData.Strings.EXTENDED_DESCRIPTION[4], true);

                Initialize(999, 0, 0, 0);

                SetScaling(Affinity.Star, 99);

                this.attackType = EYBAttackType.Normal;
                this.target = CardTarget.ENEMY;
                this.type = CardType.ATTACK;
                this.cropPortrait = false;

                LoadImage("_4");

                break;
            }
        }
    }
}