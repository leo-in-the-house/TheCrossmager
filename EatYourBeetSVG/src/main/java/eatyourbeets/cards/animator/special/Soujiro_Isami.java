package eatyourbeets.cards.animator.special;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.series.LogHorizon.Soujiro;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Soujiro_Isami extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Soujiro_Isami.class)
            .SetAttack(1, CardRarity.SPECIAL)
            .SetSeries(Soujiro.DATA.Series);

    public Soujiro_Isami()
    {
        super(DATA);

        Initialize(6, 0, 7);
        SetUpgrade(4, 0, 0);

        SetAffinity_Red(1, 0, 1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.DealDamage(this, m, AttackEffects.SLASH_DIAGONAL);

        GameActions.Bottom.ModifyAllCopies(Soujiro.DATA.ID)
        .AddCallback(info, (info2, c) ->
        {
            GameUtilities.IncreaseDamage(c, magicNumber, false);
            c.flash();
        });
    }
}