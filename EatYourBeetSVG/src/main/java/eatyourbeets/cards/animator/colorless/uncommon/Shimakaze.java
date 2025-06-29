package eatyourbeets.cards.animator.colorless.uncommon;

import com.megacrit.cardcrawl.cards.status.Dazed;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.status.Status_Dazed;
import eatyourbeets.cards.base.*;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.utilities.GameActions;

public class Shimakaze extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Shimakaze.class)
            .SetAttack(1, CardRarity.UNCOMMON).SetColor(CardColor.COLORLESS)
            .SetSeries(CardSeries.Kancolle)
            .PostInitialize(data -> data.AddPreview(GetClassCard(Dazed.ID), false));

    public Shimakaze()
    {
        super(DATA);

        Initialize(3, 3, 3);
        SetUpgrade(1, 1, 1);
        
        SetAffinity_Green(1, 0, 1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.DealDamage(this, m, AttackEffects.BLUNT_LIGHT);
        GameActions.Bottom.GainBlock(block);

        GameActions.Bottom.Draw(magicNumber);
        GameActions.Bottom.MakeCardInDrawPile(new Status_Dazed());
    }
}