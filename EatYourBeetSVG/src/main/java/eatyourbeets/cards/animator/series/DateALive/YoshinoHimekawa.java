package eatyourbeets.cards.animator.series.DateALive;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.Frost;
import eatyourbeets.actions.animator.ApplyAmountToOrbs;
import eatyourbeets.cards.animator.special.Zadkiel;
import eatyourbeets.cards.base.*;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class YoshinoHimekawa extends AnimatorCard
{
    public static final EYBCardData DATA = Register(YoshinoHimekawa.class)
            .SetSkill(2, CardRarity.UNCOMMON, EYBCardTarget.None)
            .SetSeriesFromClassPackage();
    static
    {
        DATA.AddPreview(new Zadkiel(), true);
    }

    public YoshinoHimekawa()
    {
        super(DATA);

        Initialize(0, 4, 1, 0);
        SetUpgrade(0, 0, 1);

        SetHaste(true);

        SetAffinity_Green(1, 0, 1);
        SetAffinity_Blue(1, 0, 1);
    }

    @Override
    protected void OnUpgrade()
    {
        super.OnUpgrade();

        AddScaling(Affinity.Green, 1);
        AddScaling(Affinity.Blue, 1);
    }

    @Override
    public void triggerWhenDrawn()
    {
        if (this.hasTag(HASTE))
        {
            GameActions.Top.Discard(this, player.hand).ShowEffect(true, true)
                    .AddCallback(() -> GameActions.Top.MakeCardInDrawPile(new Zadkiel()).SetUpgrade(upgraded, false))
                    .SetDuration(0.15f, true);
        }
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.GainBlock(block);

        GameActions.Last.Callback(cards -> {
            GameActions.Bottom.Add(new ApplyAmountToOrbs(Frost.ORB_ID, magicNumber));
        });
    }
}