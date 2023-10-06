package eatyourbeets.cards.animator.series.Fate;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.special.OrbCore;
import eatyourbeets.cards.base.*;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class RinTohsaka extends AnimatorCard
{
    public static final EYBCardData DATA = Register(RinTohsaka.class)
            .SetSkill(1, CardRarity.UNCOMMON, EYBCardTarget.None)
            
            .SetSeriesFromClassPackage()
            .PostInitialize(data -> data.AddPreviews(OrbCore.GetAllCores(), false));

    public RinTohsaka()
    {
        super(DATA);

        Initialize(0, 4, 2, 0);
        SetUpgrade(0, 3, 1, 0);

        SetAffinity_Blue(1, 0, 1);

        SetAffinityRequirement(Affinity.White, 4);
        SetAffinityRequirement(Affinity.Black, 4);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.GainBlock(block);
        GameActions.Bottom.GainTemporaryArtifact(magicNumber);

        if (CheckSpecialCondition(false))
        {
            GameActions.Bottom.Add(OrbCore.SelectCoreAction(name, 1, 3)
            .AddCallback(cards ->
            {
                for (AbstractCard c : cards)
                {
                    GameActions.Bottom.MakeCardInDrawPile(c);
                }
            }));
        }
    }
}