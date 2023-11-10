package eatyourbeets.cards.animator.series.Rewrite;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.special.Kagari_Moon;
import eatyourbeets.cards.animator.special.Kagari_Terra;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.resources.GR;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Kagari extends AnimatorCard {
    public static final EYBCardData DATA = Register(Kagari.class)
            .SetPower(2, CardRarity.RARE)
            .SetSeriesFromClassPackage()
            .PostInitialize(data ->
            {
                data.AddPreview(new Kagari_Moon(), false);
                data.AddPreview(new Kagari_Terra(), false);
            });

    public Kagari() {
        super(DATA);

        Initialize(0, 0, 0);
        SetCostUpgrade(-1);

        SetInnate(true);

        SetAffinity_Brown(1);
        SetAffinity_Green(1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        CardGroup group = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        group.addToBottom(new Kagari_Moon());
        group.addToBottom(new Kagari_Terra());

        GameActions.Bottom.SelectFromPile(name, 1, group)
            .SetMessage(GR.Common.Strings.GridSelection.ChooseAndPlay)
            .AddCallback(cards -> {
                for (AbstractCard card : cards) {
                    GameActions.Top.PlayCard(card, m);
                }
            });
    }
}