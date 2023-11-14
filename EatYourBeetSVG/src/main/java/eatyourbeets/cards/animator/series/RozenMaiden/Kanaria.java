package eatyourbeets.cards.animator.series.RozenMaiden;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.effects.VFX;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.stances.TranceStance;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Kanaria extends AnimatorCard {
    public static final EYBCardData DATA = Register(Kanaria.class)
            .SetPower(3, CardRarity.RARE)
            .SetSeriesFromClassPackage();

    public Kanaria() {
        super(DATA);

        Initialize(0, 0, 5);
        SetUpgrade(0, 0, 5);

        SetAffinity_Yellow(2);
        SetAffinity_Black(2);

        SetEthereal(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.ChangeStance(TranceStance.STANCE_ID);
        GameActions.Bottom.StackPower(new KanariaPower(p, magicNumber));
    }

    public static class KanariaPower extends AnimatorPower {
        public KanariaPower(AbstractCreature owner, int amount) {
            super(owner, Kanaria.DATA);
            Initialize(amount);
        }

        private final int AMOUNT_TO_INCREASE = 3;

        @Override
        public void updateDescription() {
            description = FormatDescription(0, amount);
        }

        @Override
        public void onPlayCard(AbstractCard card, AbstractMonster m) {
            super.onPlayCard(card, m);

            if (GameUtilities.InStance(TranceStance.STANCE_ID) && (card.retain || card.selfRetain)) {

                int[] damageMatrix = DamageInfo.createDamageMatrix(amount, true);
                GameActions.Bottom.VFX(VFX.ShockWave(player.hb, Color.GREEN), 0.3f);
                GameActions.Bottom.DealDamageToAll(damageMatrix, DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.NONE)
                        .AddCallback(enemy -> {
                            amount += AMOUNT_TO_INCREASE;
                        });

                flash();
            }
        }
    }
}