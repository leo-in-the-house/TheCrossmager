package eatyourbeets.cards.animator.series.Atelier;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.special.Plachta;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBAttackType;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.utilities.CardSelection;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class SophieNeuenmuller extends AnimatorCard {
    public static final EYBCardData DATA = Register(SophieNeuenmuller.class)
            .SetAttack(2, CardRarity.RARE, EYBAttackType.Elemental)
            .SetSeriesFromClassPackage();
    static
    {
        DATA.AddPreview(new Plachta(), false);
    }

    public SophieNeuenmuller() {
        super(DATA);

        Initialize(2, 0, 1);
        SetUpgrade(2, 0, 1);

        SetAffinity_White(1);
        SetAffinity_Blue(1);

        SetDelayed(true);
        SetUnique(true, true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.DealDamage(this, m, AttackEffects.LIGHTNING);

        GameActions.Bottom.GainPlatedArmor(magicNumber);
        GameActions.Bottom.ChannelRandomOrb(1);

        if (CombatStats.TryActivateLimited(cardID)) {
            GameActions.Bottom.MakeCardInHand(new Plachta())
            .AddCallback(card -> {
                for (int i=0; i<(timesUpgraded - 1); i++) {
                    card.upgrade();
                }

                if (card instanceof Plachta) {
                    ((Plachta) card).setLinkedUUID(uuid);
                }
            });

            GameActions.Last.MoveCard(this, p.drawPile)
            .ShowEffect(true, true)
            .SetDestination(CardSelection.Random);
        }
    }
}