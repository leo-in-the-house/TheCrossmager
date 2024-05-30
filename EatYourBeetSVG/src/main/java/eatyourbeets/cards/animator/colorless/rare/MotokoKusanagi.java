package eatyourbeets.cards.animator.colorless.rare;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class MotokoKusanagi extends AnimatorCard {
    public static final EYBCardData DATA = Register(MotokoKusanagi.class)
            .SetAttack(1, CardRarity.RARE, EYBAttackType.Ranged, EYBCardTarget.Normal)
            .SetColor(CardColor.COLORLESS)
            .SetSeriesFromClassPackage();

    public MotokoKusanagi() {
        super(DATA);

        Initialize(4, 0, 4);
        SetUpgrade(0, 0, 1);

        SetSeries(CardSeries.GhostInTheShell);
        SetExhaust(true);
        SetDelayed(true);

        SetRicochet(5, 0, this::OnCooldownCompleted);

        SetAffinity_Teal(1, 0, 1);
        SetAffinity_Black(1, 0, 1);
    }

    @Override
    public void triggerOnExhaust()
    {
        GameActions.Bottom.GainIntangible(1);
    }

    @Override
    public AbstractAttribute GetDamageInfo()
    {
        return super.GetDamageInfo().AddMultiplier(magicNumber);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        for (int i=0; i<magicNumber; i++) {
            GameActions.Bottom.DealDamage(this, m, AttackEffects.GUNSHOT);
        }
    }

    protected void OnCooldownCompleted(AbstractMonster m)
    {
        GameActions.Bottom.MoveCard(this, player.exhaustPile, player.hand)
                .ShowEffect(true, false);
    }
}