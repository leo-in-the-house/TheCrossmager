package eatyourbeets.cards.animator.series.Atelier;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class ShallotteElminus extends AnimatorCard {
    public static final EYBCardData DATA = Register(ShallotteElminus.class)
            .SetAttack(0, CardRarity.UNCOMMON)
            .SetSeriesFromClassPackage();

    public ShallotteElminus() {
        super(DATA);

        Initialize(2, 2, 2);
        SetUpgrade(0, 0, 1);

        SetAffinity_Green(1);
        SetAffinity_Blue(1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.DealDamage(this, m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        GameActions.Bottom.GainBlock(block);
    }

    @Override
    public void onRetained()
    {
        super.onRetained();

        ActivateLotte();
    }

    @Override
    public void triggerOnManualDiscard()
    {
        super.triggerOnManualDiscard();
        ActivateLotte();
    }

    private void ActivateLotte() {
        if (CombatStats.TryActivateSemiLimited(cardID)) {
            GameActions.Bottom.Flash(this);
            GameActions.Bottom.Callback(() -> CombatStats.Affinities.AddAffinitySealUses(1));
            GameActions.Bottom.MakeCardInHand(this.makeStatEquivalentCopy())
                    .Repeat(magicNumber);
        }
    }
}