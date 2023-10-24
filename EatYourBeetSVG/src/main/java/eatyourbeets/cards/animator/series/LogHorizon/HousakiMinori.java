package eatyourbeets.cards.animator.series.LogHorizon;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.monsters.EnemyIntent;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class HousakiMinori extends AnimatorCard
{
    public static final EYBCardData DATA = Register(HousakiMinori.class)
            .SetSkill(2, CardRarity.COMMON, EYBCardTarget.None)
            .SetSeriesFromClassPackage();

    public HousakiMinori()
    {
        super(DATA);

        Initialize(0, 0, 4);
        SetUpgrade(0, 0, 4);

        SetAffinity_White(2);

        SetDelayed(true);
    }

    @Override
    public void OnDrag(AbstractMonster m)
    {
        super.OnDrag(m);

        for (EnemyIntent intent : GameUtilities.GetIntents())
        {
            intent.AddPlayerEnchantedArmor(magicNumber);
        }
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.GainTemporaryEnchantedArmor(magicNumber);
    }
}