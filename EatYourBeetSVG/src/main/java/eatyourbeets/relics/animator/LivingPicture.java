package eatyourbeets.relics.animator;

import basemod.abstracts.CustomSavable;
import eatyourbeets.cards.animator.enchantments.Enchantment;
import eatyourbeets.cards.base.EYBCard;
import eatyourbeets.interfaces.listeners.OnEquipUnnamedReignRelicListener;
import eatyourbeets.interfaces.subscribers.OnAffinitySealedSubscriber;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.relics.EnchantableRelic;
import eatyourbeets.utilities.GameActions;

public class LivingPicture extends EnchantableRelic implements CustomSavable<Integer>, OnEquipUnnamedReignRelicListener, OnAffinitySealedSubscriber
{
    public static final String ID = CreateFullID(LivingPicture.class);
    public static final int DRAW_CARDS = 2;

    public LivingPicture()
    {
        this(null);
    }

    public LivingPicture(Enchantment enchantment)
    {
        super(ID, RelicTier.STARTER, LandingSound.MAGICAL, enchantment);
    }

    @Override
    public String getUpdatedDescription()
    {
        return FormatDescription(0, DRAW_CARDS);
    }

    @Override
    public void OnEquipUnnamedReignRelic()
    {

    }

    @Override
    public void atBattleStart()
    {
        super.atBattleStart();

        SetEnabled(true);
    }

    @Override
    public void onVictory()
    {
        super.onVictory();

        SetEnabled(true);
    }

    @Override
    protected void ActivateBattleEffect()
    {
        super.ActivateBattleEffect();

        CombatStats.onAffinitySealed.Subscribe(this);
    }

    @Override
    protected void DeactivateBattleEffect()
    {
        super.DeactivateBattleEffect();

        CombatStats.onAffinitySealed.Unsubscribe(this);
    }

    @Override
    public void OnAffinitySealed(EYBCard card, boolean manual)
    {
        if (IsEnabled() && player.hand.contains(card))
        {
            GameActions.Bottom.Draw(DRAW_CARDS);
            SetEnabled(false);
            flash();
        }
    }
}