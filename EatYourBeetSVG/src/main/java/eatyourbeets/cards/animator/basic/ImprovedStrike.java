package eatyourbeets.cards.animator.basic;

import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.effects.AttackEffects;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.Affinity;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.resources.GR;
import eatyourbeets.utilities.GameActions;

import java.util.ArrayList;

public abstract class ImprovedStrike extends ImprovedBasicCard
{
    public static final ArrayList<EYBCardData> list = new ArrayList<>();

    public static ArrayList<EYBCardData> GetCards()
    {
        if (list.isEmpty())
        {
            list.add(Strike_Red.DATA);
            list.add(Strike_Green.DATA);
            list.add(Strike_Blue.DATA);
            list.add(Strike_Light.DATA);
            list.add(Strike_Dark.DATA);
            list.add(Strike_Brown.DATA);
            list.add(Strike_Pink.DATA);
            list.add(Strike_Teal.DATA);
            list.add(Strike_Violet.DATA);
            list.add(Strike_Yellow.DATA);
        }

        return list;
    }

    protected static EYBCardData Register(Class<? extends AnimatorCard> type)
    {
        return AnimatorCard.Register(type)
        .SetColor(CardColor.COLORLESS).SetAttack(1, CardRarity.BASIC)
        .SetImagePath(GR.GetCardImage(Strike.DATA.ID + "Alt1"));
    }

    public ImprovedStrike(EYBCardData data, Affinity affinity)
    {
        super(data, affinity, GR.GetCardImage(Strike.DATA.ID + "Alt2"));

        if (affinity == Affinity.Star)
        {
            Initialize(5, 0);
            SetUpgrade(3, 0);
        }
        else
        {
            Initialize(6, 0);
            SetUpgrade(3, 0);
        }

        SetTag(CardTags.STARTER_STRIKE, true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.DealDamage(this, m, AttackEffects.SLASH_DIAGONAL);
    }
}