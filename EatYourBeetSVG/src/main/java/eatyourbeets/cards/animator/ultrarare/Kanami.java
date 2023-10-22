package eatyourbeets.cards.animator.ultrarare;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.WhirlwindEffect;
import eatyourbeets.cards.base.*;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameEffects;
import eatyourbeets.utilities.GameUtilities;

public class Kanami extends AnimatorCard_UltraRare
{
    public static final EYBCardData DATA = Register(Kanami.class)
            .SetAttack(1, CardRarity.SPECIAL, EYBAttackType.Normal, EYBCardTarget.ALL)
            .SetColor(CardColor.COLORLESS)
            .SetSeries(CardSeries.LogHorizon)
            .PostInitialize(data -> data.AddPreview(new KanamiAlt(), true));

    public Kanami()
    {
        super(DATA);

        Initialize(20, 0);
        SetUpgrade(4, 0);

        SetAffinity_Red(2, 0, 2);
        SetAffinity_Green(2, 0, 2);

        SetCooldown(1, 0, this::OnCooldownCompleted);
        SetDelayed(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.DealDamageToAll(this, AttackEffects.SLASH_HORIZONTAL)
        .SetVFX(true, false)
        .AddCallback(enemies ->
        {
            CardCrawlGame.sound.play("ATTACK_WHIRLWIND");
            for (AbstractCreature c : enemies)
            {
                GameActions.Top.VFX(new WhirlwindEffect(), 0);
                GameEffects.List.Add(new WhirlwindEffect());
            }
        });

        GameActions.Bottom.ExhaustFromPile(name, player.discardPile.size(), player.discardPile)
           .SetOptions(false, true);

        GameActions.Last.Callback(() -> {
            cooldown.ProgressCooldownAndTrigger(null);
        });
    }

    private void OnCooldownCompleted(AbstractMonster m)
    {
        GameActions.Last.ReplaceCard(uuid, new KanamiAlt())
        .SetUpgrade(upgraded)
        .AddCallback(cardMap ->
        {
            for (AbstractCard key : cardMap.keySet())
            {
                ((KanamiAlt) cardMap.get(key)).SetScaling(affinities);
            }
        });
    }
}