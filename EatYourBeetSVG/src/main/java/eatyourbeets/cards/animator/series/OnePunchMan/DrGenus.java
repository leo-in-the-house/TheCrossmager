package eatyourbeets.cards.animator.series.OnePunchMan;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.JUtils;
import eatyourbeets.utilities.RandomizedList;

public class DrGenus extends AnimatorCard
{
    public static final EYBCardData DATA = Register(DrGenus.class)
            .SetSkill(1, CardRarity.UNCOMMON, EYBCardTarget.None)
            .SetSeriesFromClassPackage();

    private Affinity cardAffinity;

    public DrGenus()
    {
        this(null);
    }

    private DrGenus(Affinity cardAffinity)
    {
        super(DATA);

        Initialize(0, 0, 3);
        SetUpgrade(0, 0, 1);

        if ((this.cardAffinity = cardAffinity) == null)
        {
            SetAffinity_Pink(1);
            SetAffinity_Teal(1);

            SetExhaust(true);
        }
        else
        {
            cardText.OverrideDescription(JUtils.Format(DATA.Strings.EXTENDED_DESCRIPTION[0], cardAffinity.GetTooltip().id), true);
        }
    }

    @Override
    protected void OnUpgrade()
    {
        SetRetain(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.FetchFromPile(name, 1, p.discardPile)
        .SetOptions(false, false)
        .SetFilter(c -> !c.hasTag(VOLATILE))
        .AddCallback(info, (info2, cards) ->
        {
           for (AbstractCard c : cards)
           {
               RandomizedList<DrGenus> cardOptions = new RandomizedList<>();

               for (Affinity affinity : Affinity.Basic()) {
                   if (affinity.ID == Affinity.Sealed.ID || affinity.ID == Affinity.Star.ID) {
                       continue;
                   }

                   cardOptions.Add(new DrGenus(affinity));
               }

               final CardGroup group = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);

               for (int i=0; i<magicNumber; i++) {
                   group.addToBottom(cardOptions.Retrieve(rng, true));
               }

               GameActions.Bottom.SelectFromPile(name, 1, group)
               .SetOptions(false, false)
               .CancellableFromPlayer(false)
               .AddCallback(c, (selected, drGenus) -> ((DrGenus)drGenus.get(0)).ApplyEffect(selected));
           }
        });
    }

    @Override
    public boolean CheckSpecialCondition(boolean tryUse)
    {
        return super.CheckSpecialConditionLimited(tryUse, super::CheckSpecialCondition);
    }

    protected void ApplyEffect(AbstractCard card)
    {
        if (cardAffinity != null) {
            GameActions.Bottom.IncreaseScaling(card, cardAffinity, 2);
        }
    }
}