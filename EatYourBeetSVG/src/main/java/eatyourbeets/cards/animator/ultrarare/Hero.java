package eatyourbeets.cards.animator.ultrarare;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.random.Random;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import eatyourbeets.cards.base.AnimatorCard_UltraRare;
import eatyourbeets.cards.base.CardSeries;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Hero extends AnimatorCard_UltraRare
{
    public static final EYBCardData DATA = Register(Hero.class)
            .SetAttack(1, CardRarity.SPECIAL)
            .SetColor(CardColor.COLORLESS)
            .SetSeries(CardSeries.GoblinSlayer);

    public Hero()
    {
        super(DATA);

        Initialize(8, 0, 40, 9);
        SetUpgrade(2, 0, 10);

        SetAffinity_Red(1, 0, 1);
        SetAffinity_Green(1, 0, 1);
        SetAffinity_White(2, 0, 2);
    }


    @Override
    public void triggerOnEndOfTurnForPlayingCard()
    {
        super.triggerOnEndOfTurnForPlayingCard();

        if (player.hand.contains(this))
        {
            int numHindrances = 0;

            for (AbstractCard card : player.exhaustPile.group) {
                if (GameUtilities.IsHindrance(card)) {
                    numHindrances++;
                }
            }
            for (AbstractCard card : player.discardPile.group) {
                if (GameUtilities.IsHindrance(card)) {
                    numHindrances++;
                }
            }

            if (numHindrances >= secondaryValue) {
                GameActions.Bottom.Flash(this);
                GameUtilities.Retain(this);
            }
        }
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.DealDamage(this, m, AttackEffects.SLASH_HEAVY)
        .AddCallback(c ->
        {
            if (GameUtilities.IsFatal(c, true) && info.TryActivateLimited())
            {
                final AbstractCard deckInstance = GameUtilities.GetMasterDeckInstance(uuid);
                if (deckInstance == null)
                {
                    return;
                }

                final Random rng = new Random(Settings.seed + (AbstractDungeon.actNum * 17) + (AbstractDungeon.floorNum * 23));
                if (rng.randomBoolean(magicNumber / 100f))
                {
                    final AbstractRelic.RelicTier tier;
                    final int roll = rng.random(0, 99);
                    if (roll < 70)
                    {
                        tier = AbstractRelic.RelicTier.COMMON;
                    }
                    else if (roll < 92)
                    {
                        tier = AbstractRelic.RelicTier.UNCOMMON;
                    }
                    else
                    {
                        tier = AbstractRelic.RelicTier.RARE;
                    }

                    AbstractDungeon.getCurrRoom().addRelicToRewards(tier);
                    deckInstance.misc = 0;
                }
                else
                {
                    deckInstance.misc += 1;
                }
            }
        });
    }
}