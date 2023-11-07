package eatyourbeets.cards.animator.special;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
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
        SetUpgrade(6, 0, 0);

        SetAffinity_Teal(2, 0, 4);
        SetAffinity_White(2, 0, 4);

        SetRetain(true);
    }

    @Override
    protected void OnUpgrade()
    {
        super.OnUpgrade();

        AddScaling(Affinity.Teal, 4);
        AddScaling(Affinity.White, 4);
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
        GameActions.Bottom.VFX(VFX.Cataclysm());
        GameActions.Bottom.WaitRealtime(0.5f);

        GameActions.Bottom.DealDamageToAll(this, AttackEffects.FIRE);
    }
}