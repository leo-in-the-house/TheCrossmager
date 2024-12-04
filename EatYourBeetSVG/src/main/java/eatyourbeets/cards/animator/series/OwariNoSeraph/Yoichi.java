package eatyourbeets.cards.animator.series.OwariNoSeraph;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Yoichi extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Yoichi.class)
            .SetSkill(0, CardRarity.COMMON, EYBCardTarget.None)
            .SetSeries(CardSeries.OwariNoSeraph);

    public Yoichi()
    {
        super(DATA);

        Initialize(0,0,1);
        SetUpgrade(0, 0, 1);

        SetAffinity_Green(1);
    }

    @Override
    protected void OnUpgrade() {
        super.OnUpgrade();

        SetHaste(true);
        SetDelayed(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.ExhaustFromHand(name, 1, false)
            .AddCallback(cards -> {
               if (cards.size() > 0) {
                   GameActions.Top.GainSupportDamage(magicNumber);
               }
            });
    }
}