package eatyourbeets.cards.animator.basic.seriespokemon;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.ShockWaveEffect;
import eatyourbeets.cards.animator.basic.pokemon.PokemonCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBAttackType;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.cards.base.modifiers.CostModifiers;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.effects.VFX;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.JUtils;

public class Kricketune extends PokemonCard {
    public static final EYBCardData DATA = Register(Kricketune.class)
            .SetAttack(1, CardRarity.BASIC, EYBAttackType.Normal, EYBCardTarget.ALL);

    public Kricketune() {
        super(DATA);

        Initialize(7, 0, 0);
        SetUpgrade(3, 0, 0);

        SetAffinity_Green(1);
    }

    @Override
    public void triggerOnOtherCardPlayed(AbstractCard c)
    {
        super.triggerOnOtherCardPlayed(c);

        GameActions.Bottom.Callback(this::RefreshCost);
    }

    @Override
    public void Refresh(AbstractMonster enemy)
    {
        super.Refresh(enemy);

        RefreshCost();
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.VFX(VFX.ShockWave(p.hb, Color.GREEN, ShockWaveEffect.ShockWaveType.NORMAL));
        GameActions.Bottom.Wait(0.5f);
        GameActions.Bottom.DealDamageToAll(this, AttackEffects.NONE);
    }

    public void RefreshCost()
    {
        CostModifiers.For(this).Set(-1 * JUtils.Count(player.hand.group, card -> card.cost == 0 && !card.uuid.equals(this.uuid)));
    }
}