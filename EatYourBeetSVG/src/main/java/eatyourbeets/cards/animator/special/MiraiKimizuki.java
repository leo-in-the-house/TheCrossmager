package eatyourbeets.cards.animator.special;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.BorderLongFlashEffect;
import eatyourbeets.cards.base.*;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.effects.VFX;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class MiraiKimizuki extends AnimatorCard {
    public static final EYBCardData DATA = Register(MiraiKimizuki.class)
            .SetAttack(4, CardRarity.SPECIAL, EYBAttackType.Elemental, EYBCardTarget.ALL)
            .SetColor(CardColor.COLORLESS)
            .SetSeries(CardSeries.OwariNoSeraph);

    public MiraiKimizuki() {
        super(DATA);

        Initialize(24, 0, 0);
        SetUpgrade(24, 0, 0);

        SetAffinity_Teal(2, 0, 9);
        SetAffinity_White(2, 0, 9);

        SetRetain(true);
    }

    @Override
    public void triggerWhenCreated(boolean startOfBattle)
    {
        super.triggerWhenCreated(startOfBattle);

        if (startOfBattle) {
            GameActions.Bottom.Exhaust(this);
        }
    }


    @Override
    public void triggerWhenDrawn() {
        super.triggerWhenDrawn();

        int numCardsInExhaust = player.exhaustPile.size();

        if (numCardsInExhaust > 0) {
            GameActions.Bottom.GainTeal(numCardsInExhaust);
            GameActions.Bottom.GainWhite(numCardsInExhaust);
        }
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.VFX(new BorderLongFlashEffect(Color.YELLOW));
        GameActions.Bottom.WaitRealtime(0.4f);
        GameActions.Bottom.VFX(VFX.Cataclysm());
        GameActions.Bottom.WaitRealtime(3f);

        GameActions.Bottom.DealDamageToAll(this, AttackEffects.FIRE);
    }
}