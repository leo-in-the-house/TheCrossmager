package eatyourbeets.cards.animator.series.LegendOfHeroesTrails;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.Lightning;
import eatyourbeets.cards.animator.special.EstelleBright_Joshua;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class EstelleBright extends AnimatorCard {
    public static final EYBCardData DATA = Register(EstelleBright.class)
            .SetSkill(2, CardRarity.RARE, EYBCardTarget.None)
            .SetSeriesFromClassPackage()
            .PostInitialize(data ->
            {
                data.AddPreview(new EstelleBright_Joshua(), false);
            });

    public EstelleBright() {
        super(DATA);

        Initialize(0, 3, 2, 3);
        SetUpgrade(0, 2, 0);

        SetAffinity_Red(1, 0, 1);
        SetAffinity_White(1, 0, 1);

        SetExhaust(true);
    }

    @Override
    public AbstractAttribute GetBlockInfo()
    {
        return super.GetBlockInfo().AddMultiplier(magicNumber);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        for (int i=0; i<magicNumber; i++) {
            GameActions.Bottom.GainBlock(block);
        }

        GameActions.Bottom.ChannelOrbs(Lightning::new, secondaryValue)
            .AddCallback(() -> {
                int numLightningChanneled = 0;

                for (AbstractOrb orb : AbstractDungeon.actionManager.orbsChanneledThisCombat) {
                    if (orb.ID.equals(Lightning.ORB_ID)) {
                        numLightningChanneled++;
                    }
                }

                for (int i=0; i<numLightningChanneled; i++) {
                    GameActions.Bottom.MakeCardInDrawPile(new EstelleBright_Joshua())
                        .SetUpgrade(upgraded, true);
                }
            });
    }
}