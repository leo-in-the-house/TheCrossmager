package eatyourbeets.cards.animator.colorless.rare;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.special.TanyaDegurechaff_Type95;
import eatyourbeets.cards.base.*;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.utilities.CardSelection;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class TanyaDegurechaff extends AnimatorCard
{
    public static final EYBCardData DATA = Register(TanyaDegurechaff.class)
            .SetAttack(1, CardRarity.RARE, EYBAttackType.Ranged)
            .SetColor(CardColor.COLORLESS)
            .SetSeries(CardSeries.YoujoSenki)
            .PostInitialize(data -> data.AddPreview(new TanyaDegurechaff_Type95(), true));

    public TanyaDegurechaff()
    {
        super(DATA);

        Initialize(4, 0, 3);
        SetUpgrade(3, 0, 1);

        SetAffinity_Red(1, 0, 1);
        SetAffinity_Violet(1, 0, 0);

        SetAffinityRequirement(Affinity.Red, 5);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.GainBlock(block);
        GameActions.Bottom.DealDamage(this, m, AttackEffects.GUNSHOT).SetSoundPitch(0.75f, 0.8f);
        GameActions.Bottom.DiscardFromPile(name, magicNumber, p.drawPile)
        .ShowEffect(true, true)
        .SetFilter(GameUtilities::IsHindrance)
        .SetOptions(CardSelection.Top, true)
        .AddCallback(cards -> {
            if (cards.size() > 0) {
                GameActions.Top.GainRed(cards.size());
            }
        });

        if (CheckSpecialCondition(false))
        {
            GameActions.Bottom.MakeCardInDrawPile(new TanyaDegurechaff_Type95())
                    .SetUpgrade(true, true);
        }
    }

    @Override
    public boolean CheckSpecialCondition(boolean tryUse)
    {
        return super.CheckSpecialConditionLimited(tryUse, super::CheckSpecialCondition);
    }
}