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
import eatyourbeets.cards.base.EYBAttackType;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class GoblinSlayer extends AnimatorCard
{
    public static final EYBCardData DATA = Register(GoblinSlayer.class)
            .SetAttack(1, CardRarity.RARE, EYBAttackType.Normal)
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

        Initialize(1, 0, 0, 2);
        SetUpgrade(0, 0, 0, 2);

        SetAffinity_Red(1, 0, 1);
        SetAffinity_Green(1, 0, 1);
        SetAffinity_Violet(1, 0, 1);

        SetRetain(true);
    }

    @Override
    protected float GetInitialDamage()
    {
        int additionalDamage = 0;

        if (GameUtilities.InGame()) {
            for (AbstractCard card : player.exhaustPile.group) {
                if (GameUtilities.IsHindrance(card)) {
                    additionalDamage += secondaryValue;
                }
            }

            for (AbstractCard card : player.discardPile.group) {
                if (GameUtilities.IsHindrance(card)) {
                    additionalDamage += secondaryValue;
                }
            }

            for (AbstractCard card : player.hand.group) {
                if (GameUtilities.IsHindrance(card)) {
                    additionalDamage += secondaryValue;
                }
            }
        }

        return super.GetInitialDamage() + additionalDamage;
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

        GameActions.Bottom.MoveCards(p.hand, p.exhaustPile)
        .SetFilter(GameUtilities::IsHindrance)
        .ShowEffect(true, true, 0.25f)
        .AddCallback(cards -> {
            GameActions.Top.MoveCards(p.discardPile, p.exhaustPile)
            .SetFilter(GameUtilities::IsHindrance)
            .ShowEffect(true, true, 0.12f)
            .AddCallback(cards2 ->
            {
                GameActions.Top.DealDamage(this, m, AttackEffects.SLASH_VERTICAL);
            });
        });
    }
}