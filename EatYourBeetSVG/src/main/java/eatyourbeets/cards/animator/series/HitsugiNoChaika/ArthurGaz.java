package eatyourbeets.cards.animator.series.HitsugiNoChaika;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import eatyourbeets.cards.animator.colorless.uncommon.ChaikaKamaz;
import eatyourbeets.cards.base.*;
import eatyourbeets.interfaces.listeners.OnAddToDeckListener;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.RandomizedList;

public class ArthurGaz extends AnimatorCard implements OnAddToDeckListener
{
    public static final EYBCardData DATA = Register(ArthurGaz.class)
            .SetSkill(1, CardRarity.RARE, EYBCardTarget.None)

            .SetSeries(CardSeries.HitsugiNoChaika)
            .PostInitialize(data ->
            {
                data.AddPreview(new ChaikaTrabant(), true);
                data.AddPreview(new ChaikaKamaz(), true);
                data.AddPreview(new ChaikaBohdan(), true);
                data.AddPreview(new Viivi(), true);
                data.AddPreview(new Layla(), true);
            });

    public ArthurGaz()
    {
        super(DATA);

        Initialize(0, 0, 4, 6);
        SetUpgrade(0, 0, 0, -2);

        SetAffinity_Teal(1);
        SetAffinity_Violet(1);

        SetRetain(true);
        SetExhaust(true);
    }

    @Override
    protected void OnUpgrade()
    {
        SetInnate(true);
    }

    @Override
    public boolean OnAddToDeck()
    {
        RandomizedList<AbstractCard> cards = new RandomizedList<>();

        cards.Add(new ChaikaTrabant());
        cards.Add(new ChaikaKamaz());
        cards.Add(new ChaikaBohdan());
        cards.Add(new Viivi());
        cards.Add(new Layla());

        AbstractCard chaikaClone = cards.Retrieve(rng);

        if (upgraded) {
            chaikaClone.upgrade();
        }

        AbstractDungeon.topLevelEffectsQueue.add(new ShowCardAndObtainEffect(chaikaClone, (float) Settings.WIDTH / 3.0F, (float) Settings.HEIGHT / 2.0F, false));

        return true;
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);

        CardGroup group = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);

        for (AbstractCard card : player.masterDeck.group) {
            if (IsChaikaClone(card)) {
                AbstractCard copy = card.makeCopy();

                if (card.upgraded) {
                    copy.upgrade();
                }
                group.addToBottom(copy);
            }
        }

        GameActions.Bottom.SelectFromPile(name, 1, group)
            .SetOptions(false, false)
            .AddCallback(cards ->
            {
                for (AbstractCard card : cards) {
                    GameActions.Top.MakeCardInHand(card)
                        .AddCallback(c -> {
                            GameUtilities.ModifyCostForCombat(c, 0, false);
                        });
                }
            });
    }

    private boolean IsChaikaClone(AbstractCard card) {
        return ChaikaTrabant.DATA.ID.equals(card.cardID) ||
                ChaikaBohdan.DATA.ID.equals(card.cardID) ||
                ChaikaKamaz.DATA.ID.equals(card.cardID) ||
                Viivi.DATA.ID.equals(card.cardID) ||
                Layla.DATA.ID.equals(card.cardID);
    }

}