package eatyourbeets.cards.animator.colorless.rare;

import basemod.BaseMod;
import com.megacrit.cardcrawl.cards.AbstractCard;
import eatyourbeets.cards.base.*;
import eatyourbeets.utilities.GameUtilities;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.random.Random;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.WeightedList;

public class Aru extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Aru.class)
            .SetSkill(0, CardRarity.RARE, EYBCardTarget.None)
            .SetSeries(CardSeries.OrangeJuice)
            .SetColor(CardColor.COLORLESS);

    public Aru()
    {
        super(DATA);

        Initialize(0, 0, 5);
        SetUpgrade(0, 0, 2);

        SetAffinity_Star(1);
        SetExhaust(true);
        SetRetain(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        Random rng = CombatStats.GetCombatData(cardID + "rng", null);
        if (rng == null)
        {
            rng = new Random(Settings.seed + (AbstractDungeon.actNum * 37) + (AbstractDungeon.floorNum * 53));
            CombatStats.SetCombatData(cardID + "rng", rng);
        }

        GameActions.Bottom.DiscardFromHand(name, BaseMod.MAX_HAND_SIZE, false)
        .SetOptions(false, false, true);

        final WeightedList<AbstractCard> cards = GameUtilities.GetCardsInCombatWeighted(null);
        for (int i = 0; i < magicNumber; i++)
        {
            GameActions.Bottom.MakeCardInHand(cards.Retrieve(rng))
            .SetUpgrade(upgraded, true)
            .SetDuration(0.01f, true);
        }
    }
}