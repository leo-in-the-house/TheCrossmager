package eatyourbeets.cards.animator.colorless.rare;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.effects.SFX;
import eatyourbeets.effects.VFX;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.resources.GR;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameEffects;
import eatyourbeets.utilities.GameUtilities;

public class SakuraKinomoto extends AnimatorCard
{
    public static final EYBCardData DATA = Register(SakuraKinomoto.class)
            .SetAttack(3, CardRarity.RARE, EYBAttackType.Elemental)
            .SetColor(CardColor.COLORLESS)
            .SetSeries(CardSeries.CardcaptorSakura);

    public SakuraKinomoto()
    {
        super(DATA);

        Initialize(18, 0, 0, 3);
        SetUpgrade(8, 0, 0, 2);

        SetAffinity_Pink(2, 0, 2);
        SetAffinity_Blue(1, 0, 1);

        SetObtainableInCombat(false);
    }

    @Override
    protected float GetInitialDamage()
    {
        return super.GetInitialDamage() + player.masterDeck.size();
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.SFX(SFX.ORB_LIGHTNING_PASSIVE, 1.35f, 1.45f);
        GameActions.Bottom.DealDamage(this, m, AbstractGameAction.AttackEffect.NONE)
        .SetDamageEffect(e -> GameEffects.Queue.Add(VFX.SmallLaser(player.hb, e.hb, Color.PINK)).duration * 0.4f)
        .AddCallback(enemy ->
        {
            if (GameUtilities.InEliteOrBossRoom() && GameUtilities.IsFatal(enemy, false) && CombatStats.CanActivateLimited(cardID))
            {
                final CardGroup choices = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
                for (int i = 0; i < secondaryValue; i++)
                {
                    final AbstractCard card = AbstractDungeon.getCard(CardRarity.RARE).makeCopy();
                    if (card != null)
                    {
                        choices.group.add(card);
                    }
                }

                GameActions.Top.SelectFromPile(name, 1, choices)
                .SetMessage(GR.Common.Strings.GridSelection.ChooseOneCard)
                .SetOptions(false, true)
                .AddCallback(cards ->
                {
                    if (cards.size() > 0 && CombatStats.TryActivateLimited(cardID))
                    {
                        GameEffects.TopLevelList.ShowAndObtain(cards.get(0));
                    }
                })
                .IsCancellable(false);
                GameActions.Top.WaitRealtime(0.5f);
            }
        });
    }
}