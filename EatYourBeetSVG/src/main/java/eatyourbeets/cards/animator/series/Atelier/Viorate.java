package eatyourbeets.cards.animator.series.Atelier;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.WeightyImpactEffect;
import eatyourbeets.cards.base.*;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.JUtils;

public class Viorate extends AnimatorCard {
    public static final EYBCardData DATA = Register(Viorate.class)
            .SetAttack(1, CardRarity.COMMON, EYBAttackType.Normal, EYBCardTarget.ALL)
            .SetSeriesFromClassPackage();

    public Viorate() {
        super(DATA);

        Initialize(17, 0, 3);
        SetUpgrade(4, 0, 0);

        SetAffinity_Violet(1);
    }

    @Override
    protected void Refresh(AbstractMonster enemy)
    {
        super.Refresh(enemy);

        SetUnplayable(JUtils.Count(player.hand.group, card -> !card.uuid.equals(this.uuid)) < magicNumber);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.DiscardFromHand(name, magicNumber, true)
        .SetOptions(false, false, false)
        .AddCallback(cards -> {
            if (cards.size() >= magicNumber) {
                for (AbstractMonster monster : GameUtilities.GetEnemies(true)) {
                   GameActions.Top.DealDamage(this, monster, AttackEffects.BLUNT_HEAVY);
                   GameActions.Top.VFX(new WeightyImpactEffect(monster.hb.cX, monster.hb.cY), 0.6f, true);
                }
            }
        });
    }
}