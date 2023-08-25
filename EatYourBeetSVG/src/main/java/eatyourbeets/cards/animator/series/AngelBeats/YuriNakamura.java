package eatyourbeets.cards.animator.series.AngelBeats;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBAttackType;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.utilities.GameActions;

public class YuriNakamura extends AnimatorCard
{
    public static final EYBCardData DATA = Register(YuriNakamura.class).SetAttack(2, CardRarity.RARE, EYBAttackType.Ranged)
            .SetSeriesFromClassPackage();

    public YuriNakamura()
    {
        super(DATA);

        Initialize(5, 0, 2, 0);
        SetUpgrade(3, 0);

        SetAffinity_Red(1);
        SetAffinity_Green(1);
        SetAffinity_Light(1);

        SetExhaust(true);

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
        GameActions.Bottom.DealDamage(this, m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        GameActions.Bottom.DealDamage(this, m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        GameActions.Bottom.DealDamage(this, m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);

        GameActions.Bottom.SelectFromPile(name, magicNumber, p.exhaustPile)
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
                    GameActions.Bottom.MakeCardInDrawPile(c);
                }
            }
        });
    }
}