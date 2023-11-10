package eatyourbeets.cards.animator.series.Rewrite;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.Dark;
import com.megacrit.cardcrawl.orbs.Frost;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.orbs.animator.Fire;
import eatyourbeets.orbs.animator.Water;
import eatyourbeets.stances.CalmStance;
import eatyourbeets.stances.MagicStance;
import eatyourbeets.stances.TranceStance;
import eatyourbeets.stances.WrathStance;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Shimako extends AnimatorCard {
    public static final EYBCardData DATA = Register(Shimako.class)
            .SetSkill(1, CardRarity.UNCOMMON, EYBCardTarget.None)
            .SetSeriesFromClassPackage();

    public Shimako() {
        super(DATA);

        Initialize(0, 5, 0);
        SetUpgrade(0, 4, 0);

        SetAffinity_Blue(1, 0, 1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.GainBlock(block);

        if (GameUtilities.InStance(WrathStance.STANCE_ID)) {
            GameActions.Bottom.ChannelOrb(new Fire());
        }

        if (GameUtilities.InStance(TranceStance.STANCE_ID)) {
            GameActions.Bottom.ChannelOrb(new Frost());
        }

        if (GameUtilities.InStance(MagicStance.STANCE_ID)) {
            GameActions.Bottom.ChannelOrb(new Dark());
        }

        if (GameUtilities.InStance(CalmStance.STANCE_ID)) {
            GameActions.Bottom.ChannelOrb(new Water());
        }
    }
}