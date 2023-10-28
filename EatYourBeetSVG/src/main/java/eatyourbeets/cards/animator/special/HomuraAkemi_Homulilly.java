package eatyourbeets.cards.animator.special;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.series.MadokaMagica.HomuraAkemi;
import eatyourbeets.cards.base.*;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.cards.base.attributes.TempHPAttribute;
import eatyourbeets.utilities.GameActions;

public class HomuraAkemi_Homulilly extends AnimatorCard
{
    public static final EYBCardData DATA = Register(HomuraAkemi_Homulilly.class)
            .SetSkill(3, CardRarity.SPECIAL, EYBCardTarget.None)
            .SetSeries(HomuraAkemi.DATA.Series);

    public HomuraAkemi_Homulilly()
    {
        super(DATA);

        Initialize(0, 0, 26);
        SetUpgrade(0, 0, 12);

        SetAffinity_Teal(2);
        SetAffinity_Black(2);

        SetAffinityRequirement(Affinity.Black, 6);
    }

    @Override
    public AbstractAttribute GetSpecialInfo()
    {
        return TempHPAttribute.Instance.SetCard(this, true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameActions.Bottom.GainTemporaryHP(magicNumber);
        GameActions.Bottom.PurgeFromPile(name, 1, player.exhaustPile)
            .SetFilter(card -> card.type == CardType.CURSE)
            .SetOptions(true, true)
            .AddCallback(cards -> {
                int numCardsPurged = cards.size();

                if (numCardsPurged > 0) {
                    for (AbstractCard card : player.exhaustPile.getCardsOfType(CardType.CURSE).group) {
                        GameActions.Top.MakeCard(card.makeCopy(), player.exhaustPile);
                    }
                }
            });

        if (!CheckSpecialCondition(false)) {
            GameActions.Last.Exhaust(this);
        }
    }
}