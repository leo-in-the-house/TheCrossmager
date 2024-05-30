package eatyourbeets.cards.animator.colorless.rare;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.RainingGoldEffect;
import eatyourbeets.cards.base.*;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.effects.VFX;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameEffects;
import eatyourbeets.utilities.GameUtilities;

public class ArseneLupinIII extends AnimatorCard {
    public static final EYBCardData DATA = Register(ArseneLupinIII.class)
            .SetSkill(2, CardRarity.RARE, EYBCardTarget.None)
            .SetColor(CardColor.COLORLESS);

    public ArseneLupinIII() {
        super(DATA);

        Initialize(0, 20, 5, 10);
        SetUpgrade(0, 0, 4, 5);
        SetAffinity_Yellow(2, 0, 1);

        SetSeries(CardSeries.Lupin);

        SetPurge(true);
        SetHaste(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.GainBlock(block);

        GameActions.Bottom.Reload(name, cards ->
        {
            if (cards.size() > 0) {
                GameEffects.Queue.Add(new RainingGoldEffect(secondaryValue * cards.size()));
                for (int i=0; i<cards.size(); i++)
                {
                    GameActions.Top.Wait(0.2f);
                    GameActions.Top.GainGold(secondaryValue);
                    GameActions.Top.DealDamageToRandomEnemy(magicNumber, DamageInfo.DamageType.THORNS, AttackEffects.NONE)
                            .SetOptions(true, false)
                            .SetDamageEffect(c ->
                                    GameEffects.List.Add(VFX.ThrowDagger(c.hb, 0.15f).SetColor(Color.WHITE))
                                            .duration * 0.25f);

                }
            }
        });
    }
}