package eatyourbeets.cards.animator.ultrarare;

import com.evacipated.cardcrawl.mod.stslib.powers.StunMonsterPower;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

import java.util.LinkedList;
import java.util.List;

public class SeriousSaitama extends AnimatorCard_UltraRare
{
    public static final EYBCardData DATA = Register(SeriousSaitama.class)
            .SetSkill(X_COST, CardRarity.SPECIAL, EYBCardTarget.ALL)
            .SetColor(CardColor.COLORLESS)
            .SetSeries(CardSeries.OnePunchMan);

    public SeriousSaitama()
    {
        super(DATA);

        Initialize(0, 0);
        SetUpgrade(0, 0);

        SetAffinity_Red(2);
        SetAffinity_Green(2);

        SetPurge(true);
        SetDelayed(true);
    }

    @Override
    protected void OnUpgrade() {
        super.OnUpgrade();

        SetDelayed(false);
        SetInnate(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        final int x = GameUtilities.UseXCostEnergy(this);

        if (x > 0)
        {
            List<AnimatorCard> scalableCards = new LinkedList<>();
            scalableCards.addAll(GetScalableCards(player.drawPile));
            scalableCards.addAll(GetScalableCards(player.hand));
            scalableCards.addAll(GetScalableCards(player.discardPile));

            for (AnimatorCard card : scalableCards) {
                GameActions.Bottom.IncreaseScaling(card, Affinity.Star, x);
            }
        }

        if (x > 1)
        {
            for (AbstractMonster enemy : GameUtilities.GetEnemies(true))
            {
                GameActions.Bottom.ApplyPower(p, new StunMonsterPower(enemy, 1));
            }
        }
    }

    private List<AnimatorCard> GetScalableCards(CardGroup group) {
        List<AnimatorCard> scalableCards = new LinkedList<>();

        for (AbstractCard card : group.group) {
            if (card instanceof AnimatorCard) {
                if (GameUtilities.HasDamageOrBlock(card)) {
                    scalableCards.add((AnimatorCard) card);
                }
            }
        }

        return scalableCards;
    }
}