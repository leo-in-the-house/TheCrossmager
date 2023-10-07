package eatyourbeets.cards.animator.series.GoblinSlayer;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.actions.animator.CreateRandomGoblins;
import eatyourbeets.cards.animator.status.GoblinChampion;
import eatyourbeets.cards.animator.status.GoblinKing;
import eatyourbeets.cards.animator.status.GoblinShaman;
import eatyourbeets.cards.animator.status.GoblinSoldier;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.modifiers.DamageModifiers;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

import java.util.ArrayList;

public class GoblinSlayer extends AnimatorCard
{
    public static final EYBCardData DATA = Register(GoblinSlayer.class)
            .SetAttack(1, CardRarity.RARE)
            .SetSeriesFromClassPackage()
            .PostInitialize(data ->
            {
                data.AddPreview(new GoblinSoldier(), false);
                data.AddPreview(new GoblinShaman(), false);
                data.AddPreview(new GoblinChampion(), false);
                data.AddPreview(new GoblinKing(), false);
            });

    public GoblinSlayer()
    {
        super(DATA);

        Initialize(1, 0, 0, 3);
        SetUpgrade(0, 0, 0, 3);

        SetAffinity_Red(1, 0, 1);
        SetAffinity_Green(1, 0, 1);
        SetAffinity_Violet(1, 0, 1);

        SetRetain(true);
    }

    @Override
    public void atTurnStart()
    {
        super.atTurnStart();

        final int turnCount = CombatStats.TurnCount(true);
        if (turnCount % 2 == 1)
        {
            int goblins = 1;
            if (CombatStats.TryActivateSemiLimited(cardID))
            {
                if (turnCount > 3)
                {
                    goblins += 1;
                }
                if (turnCount > 7)
                {
                    goblins += 1;
                }
            }

            GameActions.Bottom.Add(new CreateRandomGoblins(goblins));
        }
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.DealDamage(this, m, AttackEffects.SLASH_VERTICAL);

        GameActions.Bottom.MoveCards(p.hand, p.exhaustPile)
        .SetFilter(GameUtilities::IsHindrance)
        .ShowEffect(true, true, 0.25f)
        .AddCallback(this::IncreaseDamage);

        GameActions.Bottom.MoveCards(p.discardPile, p.exhaustPile)
        .SetFilter(GameUtilities::IsHindrance)
        .ShowEffect(true, true, 0.12f)
        .AddCallback(this::IncreaseDamage);
    }

    protected void IncreaseDamage(ArrayList<AbstractCard> cards)
    {
        final int bonus = cards.size() * secondaryValue;
        if (bonus > 0)
        {
            GameActions.Bottom.ModifyAllCopies(cardID)
            .AddCallback(bonus, (mod, c) -> DamageModifiers.For(c).Add(mod));
        }
    }
}