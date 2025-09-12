package eatyourbeets.cards.animator.special;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.series.MadokaMagica.MadokaKaname;
import eatyourbeets.cards.base.Affinity;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class MadokaKaname_KriemhildGretchen extends AnimatorCard
{
    public static final EYBCardData DATA = Register(MadokaKaname_KriemhildGretchen.class)
            .SetPower(2, CardRarity.SPECIAL)
            .SetSeries(MadokaKaname.DATA.Series)
            .PostInitialize(data -> data.AddPreview(new Special_Miracle(), false));


    public MadokaKaname_KriemhildGretchen()
    {
        super(DATA);

        Initialize(0, 0);
        SetUpgrade(0, 12);

        SetAffinity_White(1);
        SetAffinity_Black(2);

        SetEthereal(true);
    }

    @Override
    protected void OnUpgrade()
    {
        super.OnUpgrade();

        AddScaling(Affinity.White, 1);
        AddScaling(Affinity.Black, 1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.GainBlock(block);

        GameActions.Bottom.StackPower(new MadokaKaname_KriemhildGretchenPower(p, 1, upgraded));
    }

    public static class MadokaKaname_KriemhildGretchenPower extends AnimatorPower
    {
        private boolean upgraded;

        public MadokaKaname_KriemhildGretchenPower(AbstractCreature owner, int amount, boolean upgraded)
        {
            super(owner, MadokaKaname_KriemhildGretchen.DATA);

            this.upgraded = upgraded;

            Initialize(amount);
        }

        @Override
        public void updateDescription()
        {
            description = FormatDescription(upgraded ? 1 : 0, amount);
        }

        @Override
        public void atStartOfTurnPostDraw()
        {
            super.atStartOfTurnPostDraw();

            GameActions.Bottom.Callback(() -> {
                for (AbstractCard card : player.hand.group) {
                    if (GameUtilities.IsHindrance(card)) {
                        GameActions.Bottom.Exhaust(card);
                    }
                }

                GameActions.Last.Callback(() -> {
                    GameActions.Top.PurgeFromPile(name, 1, player.exhaustPile)
                            .SetFilter(card -> card.type == CardType.CURSE)
                            .SetOptions(true, true)
                            .AddCallback(purgedCards -> {
                                int numCardsPurged = purgedCards.size();

                                if (numCardsPurged > 0) {
                                    GameActions.Top.MakeCardInHand(new Special_Miracle());
                                    GameActions.Top.MakeCardInHand(AbstractDungeon.getCard(CardRarity.UNCOMMON, rng))
                                            .SetUpgrade(upgraded, true);
                                }
                            });
                });
            });
        }
    }
}