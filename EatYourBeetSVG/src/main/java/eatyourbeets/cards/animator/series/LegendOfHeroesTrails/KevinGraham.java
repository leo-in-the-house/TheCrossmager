package eatyourbeets.cards.animator.series.LegendOfHeroesTrails;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.Lightning;
import eatyourbeets.cards.base.*;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.effects.VFX;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameEffects;
import eatyourbeets.utilities.GameUtilities;

public class KevinGraham extends AnimatorCard {
    public static final EYBCardData DATA = Register(KevinGraham.class)
            .SetAttack(2, CardRarity.UNCOMMON, EYBAttackType.Piercing, EYBCardTarget.Normal)
            .SetSeriesFromClassPackage();

    public KevinGraham() {
        super(DATA);

        Initialize(14, 0, 3);
        SetUpgrade(8, 0, 0);

        SetAffinity_White(2, 0, 2);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.DealDamage(this, m, AttackEffects.NONE)
                .SetDamageEffect(c -> GameEffects.List.Add(VFX.ThrowDagger(c.hb, 0.15f).SetColor(Color.GREEN)).duration * 0.5f)
                .AddCallback(m.currentBlock, (initialBlock, target) ->
                {
                    if (GameUtilities.IsDeadOrEscaped(target) || (initialBlock > 0 && target.currentBlock <= 0))
                    {
                        GameActions.Bottom.GainOrbSlots(magicNumber);
                        GameActions.Bottom.Callback(() -> {
                            GameActions.Bottom.ChannelOrbs(Lightning::new, GameUtilities.GetEmptyOrbCount());
                        });
                    }
                });

    }
}