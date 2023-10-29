package eatyourbeets.cards.animator.series.NoGameNoLife;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import eatyourbeets.utilities.GameUtilities;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.cards.base.attributes.HPAttribute;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.utilities.GameActions;

public class IzunaHatsuse extends AnimatorCard
{
    public static final EYBCardData DATA = Register(IzunaHatsuse.class)
            .SetSkill(0, CardRarity.COMMON)
            .SetSeriesFromClassPackage()
            .PostInitialize(data -> data.AddPreview(new IzunaHatsuse(true), true));

    private boolean transformed;

    public IzunaHatsuse()
    {
        this(false);
    }

    private IzunaHatsuse(boolean transformed)
    {
        super(DATA);

        Initialize(3, 0, 2, 1);
        SetUpgrade(0, 0, 1, 1);

        SetTransformed(transformed);
    }

    @Override
    public void OnDrag(AbstractMonster m)
    {
        super.OnDrag(m);

        if (m != null && !transformed)
        {
            GameUtilities.GetIntent(m).AddWeak();
        }
    }

    @Override
    public AbstractAttribute GetSpecialInfo()
    {
        return transformed ? HPAttribute.Instance.SetCardHeal(this) : null;
    }

    @Override
    public AbstractAttribute GetDamageInfo()
    {
        return transformed ? super.GetDamageInfo().AddMultiplier(magicNumber) : null;
    }

    @Override
    public AbstractCard makeStatEquivalentCopy()
    {
        final IzunaHatsuse other = (IzunaHatsuse) super.makeStatEquivalentCopy();

        other.SetTransformed(transformed);

        return other;
    }

    @Override
    public void triggerOnAffinitySeal(boolean reshuffle)
    {
        super.triggerOnAffinitySeal(reshuffle);

        if (!transformed)
        {
            SetTransformed(true);
        }
    }

    @Override
    protected void Refresh(AbstractMonster enemy)
    {
        super.Refresh(enemy);

        this.heal = transformed ? GameUtilities.GetHealthRecoverAmount(magicNumber) : 0;
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        if (transformed)
        {
            for (int i=0; i<magicNumber; i++) {
                if (i % 3 == 0) {
                    GameActions.Bottom.DealDamage(this, m, AttackEffects.SLASH_VERTICAL);
                }
                else if (i % 3 == 1) {
                    GameActions.Bottom.DealDamage(this, m, AttackEffects.SLASH_HORIZONTAL);
                }
                else {
                    GameActions.Bottom.DealDamage(this, m, AttackEffects.SLASH_DIAGONAL);
                }
            }
            GameActions.Bottom.RecoverHP(1);
        }
        else
        {
            GameActions.Bottom.ApplyWeak(p, m, secondaryValue);
        }
    }

    @Override
    public void renderUpgradePreview(SpriteBatch sb)
    {
        if (!transformed)
        {
            super.renderUpgradePreview(sb);
        }
    }

    @Override
    public EYBCardPreview GetCardPreview()
    {
        return transformed ? null : super.GetCardPreview();
    }

    private void SetTransformed(boolean value)
    {
        if (transformed != value)
        {
            transformed = value;

            if (transformed)
            {
                LoadImage("Alt");
                SetAttackType(EYBAttackType.Normal);

                affinities.sealed = false;
                affinities.Set(Affinity.Red, 1);
                affinities.Set(Affinity.White, 0);

                SetFading(true);

                this.type = CardType.ATTACK;
                this.cardText.OverrideDescription(cardData.Strings.EXTENDED_DESCRIPTION[0], true);
            }
            else
            {
                LoadImage(null);

                affinities.Set(Affinity.Red, 0);
                affinities.Set(Affinity.White, 1);

                SetHaste(true);

                this.type = CardType.SKILL;
                this.cardText.OverrideDescription(null, true);
            }
        }
    }
}