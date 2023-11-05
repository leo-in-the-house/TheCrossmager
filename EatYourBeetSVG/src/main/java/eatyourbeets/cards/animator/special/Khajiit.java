package eatyourbeets.cards.animator.special;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.cards.base.attributes.TempHPAttribute;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.TargetHelper;

public class Khajiit extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Khajiit.class)
            .SetSkill(1, CardRarity.SPECIAL, EYBCardTarget.None)
            .SetColor(CardColor.COLORLESS)
            .SetSeries(CardSeries.Overlord)
            
            .PostInitialize(data -> data.AddPreview(new Khajiit_SkeletalDragon(), true));

    private static AbstractCard preview;
    private static AbstractCard preview_upgraded;

    public Khajiit()
    {
        super(DATA);

        Initialize(0, 0, 4, 4);
        SetUpgrade(0, 0, 3);

        SetAffinity_Black(1);
        SetAffinity_Violet(1);

        SetCardPreview((cards, m) ->
        {
            if (preview == null)
            {
                preview = Khajiit_SkeletalDragon.DATA.MakeCopy(false);
                preview_upgraded = Khajiit_SkeletalDragon.DATA.MakeCopy(false);
            }

            final AbstractCard toAdd = upgraded ? preview_upgraded : preview;
            toAdd.calculateCardDamage(m);
            cards.Clear();
            cards.Add(toAdd);
        });
    }

    @Override
    public AbstractAttribute GetSpecialInfo()
    {
        return TempHPAttribute.Instance.SetCard(this, true);
    }

    @Override
    protected void Refresh(AbstractMonster enemy)
    {
        super.Refresh(enemy);

        if (CheckSpecialCondition(false))
        {
            SetAttackTarget(EYBCardTarget.Normal);
            cardPreview.SetEnabled(true);
        }
        else
        {
            SetAttackTarget(EYBCardTarget.None);
            cardPreview.SetEnabled(false);
        }
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.GainTemporaryHP(magicNumber);

        if (CheckSpecialCondition(false))
        {
            Khajiit_SkeletalDragon.DATA.MarkSeen();
            GameActions.Bottom.PlayCard(new Khajiit_SkeletalDragon(this), m);
        }
    }

    @Override
    public boolean CheckSpecialCondition(boolean tryUse)
    {
        for (AbstractMonster enemy : GameUtilities.GetEnemies(true)) {
            if (GameUtilities.GetCommonDebuffs(TargetHelper.Normal(enemy)).size() >= secondaryValue) {
                return true;
            }
        }

        return false;
    }
}