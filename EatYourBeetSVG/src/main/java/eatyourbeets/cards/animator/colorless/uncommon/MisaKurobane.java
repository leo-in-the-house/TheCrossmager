package eatyourbeets.cards.animator.colorless.uncommon;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.special.MisaKurobane_Yusarin;
import eatyourbeets.cards.base.*;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.orbs.animator.Fire;
import eatyourbeets.utilities.GameActions;

public class MisaKurobane extends AnimatorCard
{
    public static final EYBCardData DATA = Register(MisaKurobane.class)
            .SetSkill(0, CardRarity.UNCOMMON, EYBCardTarget.None)
            .SetColor(CardColor.COLORLESS)
            .SetSeries(CardSeries.Charlotte)
            .PostInitialize(data -> data.AddPreview(new MisaKurobane_Yusarin(), true));

    public MisaKurobane()
    {
        super(DATA);

        Initialize(0, 0,1);
        SetUpgrade(0, 0, 1);

        SetAffinity_Red(1);

        SetExhaust(true);
        SetEvokeOrbCount(1);

        SetAffinityRequirement(Affinity.White, 2);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.ChannelOrb(new Fire());
        GameActions.Bottom.Draw(magicNumber);

        if (TryUseAffinity(Affinity.White))
        {
            GameActions.Bottom.MakeCardInDrawPile(new MisaKurobane_Yusarin());
        }
    }
}