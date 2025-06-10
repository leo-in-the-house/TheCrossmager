package eatyourbeets.cards.animator.colorless.rare;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.OfferingEffect;
import eatyourbeets.cards.base.*;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameEffects;
import eatyourbeets.utilities.GameUtilities;

public class BlackCat extends AnimatorCard {
    public static final EYBCardData DATA = Register(BlackCat.class)
            .SetSkill(4, CardRarity.RARE, EYBCardTarget.None)
            .SetColor(CardColor.COLORLESS)
            .SetSeries(CardSeries.TheWitchsHouse)
            .PostInitialize(data ->
            {
                data.AddPreview(new Viola(), true);
                data.AddPreview(new Ellen(), true);
            });

    public BlackCat() {
        super(DATA);

        Initialize(0, 0, 5);
        SetUpgrade(0, 0, 0);
        SetCostUpgrade(-2);

        SetAffinity_Violet(2);
        SetAffinity_Black(2);

        SetDelayed(true);
        SetPurge(true);
        SetEthereal(true);
        SetHealing(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.PurgeFromPile(name, magicNumber, player.exhaustPile)
                .SetFilter(card -> card.rarity == CardRarity.UNCOMMON)
                .SetOptions(true, true)
                .AddCallback(cards -> {
                    GameActions.Bottom.VFX(new OfferingEffect(), 0.5f);

                    GameEffects.TopLevelQueue.ShowAndObtain(new Viola());
                    GameEffects.TopLevelQueue.ShowAndObtain(new Ellen());

                    GameUtilities.RemoveFromDeck(uuid);
                });
    }
}