package eatyourbeets.cards.animator.series.HitsugiNoChaika;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBAttackType;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class ChaikaBohdan extends AnimatorCard
{
    public static final EYBCardData DATA = Register(ChaikaBohdan.class)
            .SetAttack(2, CardRarity.COMMON, EYBAttackType.Normal)
            .SetSeriesFromClassPackage();

    public ChaikaBohdan()
    {
        super(DATA);

        Initialize(15, 0, 4);
        SetUpgrade(5, 0, 0);

        SetAffinity_Red(2, 0, 0);
    }

    @Override
    protected void OnUpgrade()
    {
        super.OnUpgrade();

        SetLoyal(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.DealDamage(this, m, AttackEffects.SLASH_DIAGONAL);
        GameActions.Bottom.DiscardFromHand(name, 3, false)
            .SetOptions(true, true, true)
            .AddCallback(cards -> {
                if (cards.size() > 0) {
                    GameActions.Top.ModifyAllCopies(cardID)
                        .AddCallback(c ->
                        {
                            GameUtilities.ModifyDamage(this, damage + (magicNumber * cards.size()), false);
                        });
                }
            });
    }

}