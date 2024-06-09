package eatyourbeets.cards.animator.colorless.uncommon;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class TakinaInoue extends AnimatorCard {
    public static final EYBCardData DATA = Register(TakinaInoue.class)
            .SetAttack(3, CardRarity.UNCOMMON, EYBAttackType.Ranged, EYBCardTarget.Random)
            .SetColor(CardColor.COLORLESS)
            .SetSeries(CardSeries.LycorisRecoil);

    static
    {
        DATA.AddPreview(new ChisatoNishikigi(), true);
    }

    public TakinaInoue() {
        super(DATA);

        Initialize(6, 0, 2);
        SetUpgrade(0, 0, 1);
        SetAffinity_Green(1, 0, 1);
        SetAffinity_Blue(1);
    }

    @Override
    public AbstractAttribute GetDamageInfo()
    {
        return super.GetDamageInfo().AddMultiplier(magicNumber);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        for (int i=0; i<magicNumber; i++) {
            GameActions.Bottom.DealDamageToRandomEnemy(this, AttackEffects.GUNSHOT);
        }

        GameActions.Bottom.SelectFromHand(name, magicNumber, false)
            .SetOptions(true, true, true)
            .SetFilter(GameUtilities::IsHindrance)
            .SetMessage(cardData.Strings.EXTENDED_DESCRIPTION[0])
            .AddCallback(cards ->
            {
                if (cards.size() > 0) {
                    GameActions.Top.ReplaceCard(cards.get(0).uuid, new ChisatoNishikigi());
                    GameActions.Top.GainEnergy(3);
                }
            });
    }
}