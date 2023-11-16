package eatyourbeets.cards.animator.special;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.ScreenOnFireEffect;
import eatyourbeets.cards.animator.series.TenseiSlime.Shizu;
import eatyourbeets.cards.animator.status.Status_Burn;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.powers.common.BurningPower;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Shizu_Ifrit extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Shizu_Ifrit.class)
            .SetPower(0, CardRarity.SPECIAL)
            .SetSeries(Shizu.DATA.Series)
            .PostInitialize(data -> data.AddPreview(new Status_Burn(true), false));

    public Shizu_Ifrit()
    {
        super(DATA);

        Initialize(0, 0, 100);
        SetUpgrade(0, 0, 200);

        SetAffinity_Red(2);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        final ScreenOnFireEffect effect = new ScreenOnFireEffect();
        effect.duration = effect.startingDuration = 1.5f; // Changed from 3f
        GameActions.Bottom.VFX(effect, 0.2f);
        for (AbstractCard card : player.hand.group) {
            if (!GameUtilities.IsHighCost(card)) {
                GameActions.Bottom.Exhaust(card);
                GameActions.Bottom.MakeCardInHand(new Status_Burn(true));
            }
        }
        GameActions.Bottom.Callback(() -> BurningPower.AddPlayerAttackBonus(magicNumber));
    }
}