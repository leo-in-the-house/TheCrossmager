package eatyourbeets.cards.animator.series.GATE;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.special.MoltSolAugustus_ImperialArchers;
import eatyourbeets.cards.base.*;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.utilities.CardSelection;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class ZorzalElCaesar extends AnimatorCard {
    public static final EYBCardData DATA = Register(ZorzalElCaesar.class)
            .SetAttack(2, CardRarity.RARE, EYBAttackType.Normal, EYBCardTarget.ALL)
            .SetSeriesFromClassPackage();
    static
    {
        DATA.AddPreview(new MoltSolAugustus_ImperialArchers(), false);
    }

    public ZorzalElCaesar() {
        super(DATA);

        Initialize(14, 0, 2, 0);
        SetUpgrade(4, 0, 0);

        SetAffinity_Violet(2, 0, 2);
        SetAffinity_Black(1, 0, 1);

        SetExhaust(true);
    }

    @Override
    public void triggerOnManualDiscard()
    {
        super.triggerOnManualDiscard();

        if (CombatStats.TryActivateSemiLimited(cardID)) {
            GameActions.Bottom.MakeCardInDrawPile(new MoltSolAugustus_ImperialArchers())
                    .Repeat(magicNumber);

            GameActions.Last.MoveCard(this, player.drawPile)
                    .ShowEffect(true, true)
                    .SetDestination(CardSelection.Random);
        }
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.DealDamageToAll(this, AbstractGameAction.AttackEffect.SLASH_HEAVY);

        for (AbstractCreature enemy : GameUtilities.GetEnemies(true)) {
            if (rng.randomBoolean()) {
                GameActions.Bottom.GainPestilence(1);
            }
            else {
                GameActions.Bottom.GainInsanity(1);
            }
        }
    }
}