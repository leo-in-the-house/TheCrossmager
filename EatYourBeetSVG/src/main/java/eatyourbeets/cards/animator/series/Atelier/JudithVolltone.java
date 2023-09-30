package eatyourbeets.cards.animator.series.Atelier;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.PhilosopherStone;
import com.megacrit.cardcrawl.vfx.cardManip.PurgeCardEffect;
import com.megacrit.cardcrawl.vfx.combat.OfferingEffect;
import eatyourbeets.cards.base.*;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameEffects;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.RandomizedList;

import java.util.LinkedList;
import java.util.List;

public class JudithVolltone extends AnimatorCard {
    private static final AbstractRelic relic = new PhilosopherStone();
    private static final EYBCardTooltip tooltip = new EYBCardTooltip(relic.name, relic.description);

    public static final EYBCardData DATA = Register(JudithVolltone.class)
            .SetSkill(3, CardRarity.RARE, EYBCardTarget.None)
            .SetSeriesFromClassPackage();

    public JudithVolltone() {
        super(DATA);

        Initialize(0, 0, 6);
        SetUpgrade(0, 0, -2);

        SetAffinity_Star(1);

        SetPurge(true);
        SetObtainableInCombat(false);
    }

    @Override
    public void initializeDescription()
    {
        super.initializeDescription();

        if (cardText != null)
        {
            tooltip.SetIcon(relic);
            tooltip.id = cardID + ":" + tooltip.title;
            tooltips.add(tooltip);
        }
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        List<AbstractCard> raresToRemove = GetRandomRaresOrReturnNull(magicNumber);

        if (raresToRemove != null) {
            for (AbstractCard rareCard : raresToRemove) {
                AbstractDungeon.effectList.add(new PurgeCardEffect(rareCard));
                player.masterDeck.removeCard(rareCard);
            }

            GameActions.Bottom.VFX(new OfferingEffect(), 0.5f);
            GameActions.Bottom.Callback(() -> GameEffects.Queue.SpawnRelic(relic.makeCopy(), current_x, current_y));

            //AbstractDungeon.bossRelicPool.remove(relic.relicId);

            p.energy.energy += 1;

            GameUtilities.RemoveFromDeck(uuid);
        }
    }

    private List<AbstractCard> GetRandomRaresOrReturnNull(int numRares) {

        RandomizedList<AbstractCard> possibleRaresToRemove = new RandomizedList<>();
        List<AbstractCard> raresToRemove = new LinkedList<>();

        for (AbstractCard c : player.masterDeck.group) {
            if (c.rarity == CardRarity.RARE) {
                possibleRaresToRemove.Add(c);
            }
        }

        if (possibleRaresToRemove.Size() < numRares) {
            //Not enough rares to remove!
            return null;
        }

        for (int i=0; i<numRares; i++) {
            raresToRemove.add(possibleRaresToRemove.Retrieve(rng, true));
        }

        return raresToRemove;
    }
}