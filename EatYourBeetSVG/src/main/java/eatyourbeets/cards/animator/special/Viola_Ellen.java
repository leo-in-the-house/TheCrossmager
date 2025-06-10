package eatyourbeets.cards.animator.special;

import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.InvisiblePower;
import com.megacrit.cardcrawl.actions.utility.ShakeScreenAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import eatyourbeets.cards.base.*;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.effects.EYBEffect;
import eatyourbeets.effects.VFX;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

import java.util.ArrayList;

public class Viola_Ellen extends AnimatorCard {
    public static final EYBCardData DATA = Register(Viola_Ellen.class)
            .SetAttack(1, CardRarity.SPECIAL, EYBAttackType.Normal, EYBCardTarget.Random)
            .SetSeries(CardSeries.TheWitchsHouse);

    public Viola_Ellen() {
        super(DATA);

        Initialize(9, 0, 5);
        SetUpgrade(0, 0, 0);

        SetAffinity_Violet(1, 0, 1);
        SetAffinity_Yellow(1, 0, 1);
        SetAffinity_Black(1);

        SetHaste(true);
        SetFading(true);
    }

    @Override
    protected void OnUpgrade()
    {
        super.OnUpgrade();

        SetRetain(true);
        SetLoyal(true);
    }
    @Override
    public AbstractAttribute GetDamageInfo()
    {
        return super.GetDamageInfo().AddMultiplier(magicNumber);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        int numStatusesRemoved = 0;

        final ArrayList<AbstractPower> debuffsToRemove = new ArrayList<>();
        for (AbstractPower power : player.powers)
        {
            if (power.type == AbstractPower.PowerType.DEBUFF && !(power instanceof InvisiblePower))
            {
                debuffsToRemove.add(power);
            }
        }

        numStatusesRemoved += debuffsToRemove.size();

        player.powers.removeAll(debuffsToRemove);

        for (AbstractMonster enemy : GameUtilities.GetEnemies(true)) {
            ArrayList<AbstractPower> buffsToRemove = new ArrayList<>();

            GameActions.Bottom.Add(new ShakeScreenAction(0.8f, ScreenShake.ShakeDur.MED, ScreenShake.ShakeIntensity.MED));
            GameActions.Bottom.VFX(VFX.Hemokinesis(EYBEffect.SKY_HB_R, hb).SetColor(new Color(0.7f, 0.1f, 0.7f, 1)));

            for (AbstractPower power : enemy.powers)
            {
                if (power.type == AbstractPower.PowerType.BUFF)
                {
                    buffsToRemove.add(power);
                }
            }

            numStatusesRemoved += buffsToRemove.size();
            enemy.powers.removeAll(buffsToRemove);
        }

        if (numStatusesRemoved > 0) {
            GameActions.Bottom.GainIntangible(numStatusesRemoved);
        }

        for (int i=0; i<magicNumber; i++) {
            GameActions.Bottom.DealDamageToRandomEnemy(this, AttackEffects.CLAW);
        }
    }
}