package eatyourbeets.cards.animator.series.AngelBeats;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBAttackType;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class YuriNakamura extends AnimatorCard
{
    public static final EYBCardData DATA = Register(YuriNakamura.class).SetAttack(3, CardRarity.RARE, EYBAttackType.Ranged)
            .SetSeriesFromClassPackage();

    public YuriNakamura()
    {
        super(DATA);

        Initialize(7, 0, 2, 0);
        SetUpgrade(0, 0, 1, 0);

        SetAffinity_Red(2, 0, 2);
        SetAffinity_Green(2, 0, 2);

        SetExhaust(true);
        SetEthereal(true);

    }

    @Override
    protected void OnUpgrade()
    {
        super.OnUpgrade();

        SetExhaust(false);
    }

    @Override
    public AbstractAttribute GetDamageInfo()
    {
        return super.GetDamageInfo().AddMultiplier(3);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.DealDamage(this, m, AttackEffects.GUNSHOT);
        GameActions.Bottom.DealDamage(this, m, AttackEffects.GUNSHOT);
        GameActions.Bottom.DealDamage(this, m, AttackEffects.GUNSHOT);

        GameActions.Bottom.SelectFromPile(name, 1, p.exhaustPile)
        .SetOptions(false, true,  true)
        .SetFilter(c -> c.type == CardType.ATTACK)
        .SetMessage(cardData.Strings.EXTENDED_DESCRIPTION[0])
        .AddCallback(cards ->
        {
            if (cards.size() > 0 )
            {
                AbstractCard card = cards.get(0);

                for (int i = 0; i < magicNumber; i++) {
                    AbstractCard c = card.makeCopy();
                    c.isEthereal = true;
                    GameActions.Bottom.MakeCardInDrawPile(c)
                            .SetUpgrade(upgraded, true);
                }
            }
        });
    }
}