package eatyourbeets.cards.animatorClassic.series.LogHorizon;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorClassicCard;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBAttackType;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.utilities.GameActions;

public class HousakiTohya extends AnimatorClassicCard
{
    public static final EYBCardData DATA = Register(HousakiTohya.class).SetSeriesFromClassPackage().SetAttack(1, CardRarity.COMMON, EYBAttackType.Normal);

    public HousakiTohya()
    {
        super(DATA);

        Initialize(5, 0, 1, 3);
        SetUpgrade(3, 0, 0, 0);
        SetScaling(0, 1, 1);

        
        
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.DealDamage(this, m, AttackEffects.SLASH_VERTICAL);
        GameActions.Bottom.ApplyVulnerable(player, m, magicNumber);
    }

    @Override
    public void OnLateUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameActions.Bottom.Draw(1)
        .SetFilter(c -> HousakiMinori.DATA.ID.equals(c.cardID), false)
        .AddCallback(() ->
        {
//            if (HasTeamwork(secondaryValue))
//            {
//                GameActions.Bottom.GainEnergy(1);
//            }
        });
    }
}