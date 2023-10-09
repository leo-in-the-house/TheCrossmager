package eatyourbeets.cards.animator.special;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.series.HitsugiNoChaika.AcuraTooru;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class AcuraTooru_Dragoon extends AnimatorCard
{
    public static final EYBCardData DATA = Register(AcuraTooru_Dragoon.class)
            .SetAttack(1, CardRarity.SPECIAL)
            .SetSeries(AcuraTooru.DATA.Series);

    public AcuraTooru_Dragoon()
    {
        super(DATA);

        Initialize(5, 0, 2);
        SetUpgrade(1, 0, 2);

        SetAffinity_Green(1, 0, 1);
        SetAffinity_Black(1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.DealDamage(this, m, AttackEffects.SLASH_DIAGONAL);
        GameActions.Bottom.Reload(name, cards -> {
            for (AbstractCard c : cards) {
                GameActions.Top.GainPlatedArmor(magicNumber);
            }
        });
    }
}