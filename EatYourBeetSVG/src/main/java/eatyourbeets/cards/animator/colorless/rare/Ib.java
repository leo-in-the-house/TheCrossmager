package eatyourbeets.cards.animator.colorless.rare;

import basemod.abstracts.CustomSavable;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.special.Ib_Garry;
import eatyourbeets.cards.animator.special.Ib_Mary;
import eatyourbeets.cards.base.*;
import eatyourbeets.ui.common.EYBCardPopupActions;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Ib extends AnimatorCard implements CustomSavable<Boolean> {
    public static final EYBCardData DATA = Register(Ib.class)
            .SetSkill(0, CardRarity.RARE, EYBCardTarget.None)
            .SetColor(CardColor.COLORLESS)
            .SetSeries(CardSeries.Ib)
            .PostInitialize(data ->
            {
                data.AddPopupAction(new EYBCardPopupActions.Ib_Garry(Ib_Garry.DATA, Ib_Mary.DATA));
                data.AddPopupAction(new EYBCardPopupActions.Ib_Mary(Ib_Garry.DATA, Ib_Mary.DATA));
                data.AddPopupAction(new EYBCardPopupActions.Ib_Guertena(Ib_Garry.DATA, Ib_Mary.DATA, 2));
                data.AddPreview(new Ib_Garry(), false);
                data.AddPreview(new Ib_Mary(), false);
            });

    public Ib() {
        super(DATA);

        Initialize(0, 0, 0);
        SetUpgrade(0, 0, 0);

        SetAffinity_Red(1);

        if (player != null && player.masterDeck != null) {
            boolean hasGarryOrMary = false;

            for (AbstractCard c : player.masterDeck.group) {
                if (c.cardID.equals(Ib_Garry.DATA.ID) || c.cardID.equals(Ib_Mary.DATA.ID)) {
                    hasGarryOrMary = true;
                    break;
                }
            }

            if (hasGarryOrMary) {
                LoadImage("_Upgraded");
            }
        }
    }

    @Override
    public Boolean onSave()
    {
        return true;
    }

    @Override
    public void onLoad(Boolean value)
    {
        if (player != null && player.masterDeck != null) {
            boolean hasGarryOrMary = false;

            for (AbstractCard c : player.masterDeck.group) {
                if (c.cardID.equals(Ib_Garry.DATA.ID) || c.cardID.equals(Ib_Mary.DATA.ID)) {
                    hasGarryOrMary = true;
                    break;
                }
            }

            if (hasGarryOrMary) {
                LoadImage("_Upgraded");
            }
        }
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        int numCardsInMasterDeck = 0;

        for (AbstractCard card : p.masterDeck.group) {
            if (card.type == CardType.CURSE) {
                numCardsInMasterDeck++;
            }
            else if (GameUtilities.HasAffinity(card, Affinity.Red)) {
                numCardsInMasterDeck++;
            }
        }

        if (numCardsInMasterDeck > 0) {
            GameActions.Bottom.GainTemporaryHP(numCardsInMasterDeck);
            GameActions.Bottom.GainRed(numCardsInMasterDeck);
        }
    }
}