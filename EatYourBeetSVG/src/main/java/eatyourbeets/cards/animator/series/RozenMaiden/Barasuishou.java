package eatyourbeets.cards.animator.series.RozenMaiden;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.Frost;
import eatyourbeets.actions.orbs.TriggerOrbPassiveAbility;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBAttackType;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.effects.SFX;
import eatyourbeets.effects.VFX;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameEffects;
import eatyourbeets.utilities.GameUtilities;

public class Barasuishou extends AnimatorCard {
    public static final EYBCardData DATA = Register(Barasuishou.class)
            .SetAttack(2, CardRarity.UNCOMMON, EYBAttackType.Elemental)
            .SetSeriesFromClassPackage();

    public Barasuishou() {
        super(DATA);

        Initialize(5, 0, 2);
        SetUpgrade(4, 0, 1);

        SetAffinity_Blue(1, 0, 1);
        SetAffinity_Teal(1, 0, 1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.DealDamage(this, m, AbstractGameAction.AttackEffect.NONE)
        .SetDamageEffect(c ->
        {
            SFX.Play(SFX.ATTACK_MAGIC_BEAM_SHORT, 0.9f, 1.1f, 0.95f);
            return GameEffects.List.Add(VFX.SmallLaser(player.hb, c.hb, Color.PURPLE, 0.1f)).duration * 0.7f;
        });

        GameActions.Bottom.ChannelOrbs(Frost::new, magicNumber);
        GameActions.Bottom.StackPower(new BarasuishouPower(player, 1));
    }

    public static class BarasuishouPower extends AnimatorPower {

        public BarasuishouPower(AbstractCreature owner, int amount) {
            super(owner, Barasuishou.DATA);
            Initialize(amount);
        }

        @Override
        public void updateDescription() {
            description = FormatDescription(0, amount);
        }

        @Override
        public void atEndOfTurn(boolean isPlayer) {

            int numTimesTrigger = player.hand.size() * amount;

            if (numTimesTrigger > 0) {
                for (AbstractOrb orb : player.orbs) {
                    if (orb != null && orb.ID != null && orb.ID.equals(Frost.ORB_ID)) {
                        GameActions.Bottom.Callback(new TriggerOrbPassiveAbility(orb, numTimesTrigger));
                    }
                }
            }

            flash();
            RemovePower();

            super.atEndOfTurn(isPlayer);
        }
    }
}