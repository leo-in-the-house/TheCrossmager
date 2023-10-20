package eatyourbeets.cards.animator.series.Konosuba;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.RainbowCardEffect;
import eatyourbeets.cards.base.*;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.cards.base.attributes.TempHPAttribute;
import eatyourbeets.orbs.animator.Water;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Aqua extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Aqua.class)
            .SetSkill(0, CardRarity.UNCOMMON, EYBCardTarget.None)
            
            .SetSeriesFromClassPackage()
            .PostInitialize(data -> data.AddPreview(new Aqua(true), true));

    private boolean transformed = false;

    public Aqua()
    {
        this(false);
    }

    private Aqua(boolean transformed)
    {
        super(DATA);

        Initialize(0, 0, 2, 2);
        SetUpgrade(0, 0, 0, 0);

        SetAffinity_Blue(2);

        SetTransformed(transformed);
    }

    @Override
    protected void OnUpgrade()
    {
        SetTransformed(transformed);
    }
    
    @Override
    public AbstractAttribute GetSpecialInfo()
    {
        return transformed ? null : TempHPAttribute.Instance.SetCard(this, true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        if (!transformed)
        {
            GameActions.Bottom.GainTemporaryHP(magicNumber);
            if (upgraded) {
                GameActions.Bottom.ChannelOrb(new Water());
            }

            GameActions.Bottom.FetchFromPile(name, 1, player.discardPile)
                 .SetOptions(false, false)
                 .SetFilter(card -> GameUtilities.IsSeries(card, CardSeries.Konosuba));

            GameActions.Bottom.Callback(() -> SetTransformed(true));
        }
        else
        {
            GameActions.Bottom.VFX(new RainbowCardEffect());
        }
    }

    @Override
    public AbstractCard makeStatEquivalentCopy()
    {
        Aqua other = (Aqua) super.makeStatEquivalentCopy();

        other.SetTransformed(transformed);

        return other;
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
        if (transformed)
        {
            return null;
        }

        return super.GetCardPreview();
    }

    private void SetTransformed(boolean value)
    {
        transformed = value;

        if (transformed)
        {
            LoadImage("2");
            cardText.OverrideDescription(cardData.Strings.EXTENDED_DESCRIPTION[0], true);
            type = CardType.STATUS;
            affinities.Clear();
            SetAffinity_Blue(1);
        }
        else
        {
            LoadImage(null);
            cardText.OverrideDescription(null, true);
            type = CardType.SKILL;
            affinities.Clear();
            SetAffinity_Blue(2);
        }
    }
}