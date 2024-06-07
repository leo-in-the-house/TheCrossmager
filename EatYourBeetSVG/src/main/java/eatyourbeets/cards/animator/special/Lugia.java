package eatyourbeets.cards.animator.special;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.colorless.rare.Lucius;
import eatyourbeets.cards.base.*;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.effects.SFX;
import eatyourbeets.effects.VFX;
import eatyourbeets.ui.common.EYBCardPopupActions;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Lugia extends AnimatorCard {
    public static final EYBCardData DATA = Register(Lugia.class)
            .SetAttack(4, CardRarity.SPECIAL, EYBAttackType.Elemental, EYBCardTarget.ALL)
            .SetSeries(CardSeries.Pokemon)
            .PostInitialize(data ->
            {
                data.AddPopupAction(new EYBCardPopupActions.Pokemon_Lucius(Lucius.DATA, 3));
                data.AddPreview(new Lucius(), true);
            });

    public Lugia() {
        super(DATA);

        Initialize(30, 0, 1, 15);
        SetUpgrade(10, 0, 0, -5);

        SetAffinity_Blue(2, 0, 3);

        SetRetain(true);
    }

    @Override
    protected void Refresh(AbstractMonster enemy)
    {
        super.Refresh(enemy);

        if (GameUtilities.InBattle()) {
            magicNumber = 1 + player.currentBlock / secondaryValue;
        }
        else {
            magicNumber = 1;
        }
    }

    @Override
    public AbstractAttribute GetDamageInfo()
    {
        return super.GetDamageInfo().AddMultiplier(magicNumber);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        for (int i=0; i<magicNumber; i++) {
            final int[] damageMatrix = DamageInfo.createDamageMatrix(damage, true, false);
            GameActions.Bottom.SFX(SFX.ATTACK_DEFECT_BEAM, 0.65f, 0.7f);
            GameActions.Bottom.VFX(VFX.SweepingBeam(p.hb, VFX.FlipHorizontally(), new Color(0f, 0, 1f, 1f)), 0.3f);
            GameActions.Bottom.DealDamageToAll(damageMatrix, DamageInfo.DamageType.NORMAL, AttackEffects.ICE);
        }
    }
}