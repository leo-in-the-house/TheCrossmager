package eatyourbeets.cards.animator.basic.pokemon;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.status.Status_Burn;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBAttackType;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.effects.VFX;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Flareon extends PokemonCard {
    public static final EYBCardData DATA = Register(Flareon.class)
            .SetAttack(2, CardRarity.BASIC, EYBAttackType.Elemental, EYBCardTarget.ALL)
            .PostInitialize(data -> data.AddPreview(new Status_Burn(true), false));

    public Flareon() {
        super(DATA);

        Initialize(16, 0, 0);
        SetUpgrade(3, 0, 0);

        SetAffinity_Red(1, 0, 1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);


        GameActions.Bottom.MakeCardInHand(new Status_Burn());
        for (AbstractMonster enemy : GameUtilities.GetEnemies(true))
        {
            GameActions.Bottom.VFX(VFX.Fireball(player.hb, enemy.hb).SetColor(Color.RED, Color.SCARLET).SetScale(1).SetRealtime(true));
            GameActions.Bottom.DealDamage(this, enemy, AttackEffects.NONE);
            GameActions.Bottom.WaitRealtime(0.2f);
        }
    }
}