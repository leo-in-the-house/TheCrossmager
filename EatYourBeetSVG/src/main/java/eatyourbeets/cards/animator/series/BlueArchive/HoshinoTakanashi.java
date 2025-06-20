package eatyourbeets.cards.animator.series.BlueArchive;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.LockOnPower;
import eatyourbeets.cards.base.*;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.cards.base.modifiers.CostModifiers;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.effects.vfx.megacritCopy.SmokeBombEffect2;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

import java.util.HashMap;

public class HoshinoTakanashi extends AnimatorCard {
    public static final EYBCardData DATA = Register(HoshinoTakanashi.class)
            .SetAttack(3, CardRarity.RARE, EYBAttackType.Piercing, EYBCardTarget.Random)
            .SetSeriesFromClassPackage();

    public HoshinoTakanashi() {
        super(DATA);

        Initialize(10, 0, 2);
        SetUpgrade(6, 0, 0);

        SetAffinity_Brown(2, 0, 2);
        SetAffinity_White(2, 0, 1);

        SetLoyal(true);

        SetCooldown(1, 0, this::OnCooldownCompleted);
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
            GameActions.Bottom.DealDamageToRandomEnemy(this, AttackEffects.GUNSHOT)
                .AddCallback(enemy -> {
                    if (enemy.hasPower(LockOnPower.POWER_ID) && CombatStats.TryActivateSemiLimited(cardID)) {
                        GameActions.Bottom.RecoverHP(9999);
                    }

                    GameActions.Bottom.VFX(new SmokeBombEffect2(enemy.hb.cX, enemy.hb.cY), 0.02f);
                });
        }

        cooldown.ProgressCooldownAndTrigger(m);
    }

    protected void OnCooldownCompleted(AbstractMonster m)
    {
        HashMap<String, AnimatorCard> abydosStudents = GameUtilities.GetAbydosStudents();

        GiveAbydosZeroCostHaste(abydosStudents, player.hand);
        GiveAbydosZeroCostHaste(abydosStudents, player.drawPile);
        GiveAbydosZeroCostHaste(abydosStudents, player.discardPile);
        GiveAbydosZeroCostHaste(abydosStudents, player.exhaustPile);
    }

    private void GiveAbydosZeroCostHaste(HashMap<String, AnimatorCard> abydosStudents, CardGroup group) {
        for (AbstractCard card : group.group) {
            if (abydosStudents.get(card.cardID) != null) {
                CostModifiers.For(card).Add(-99);

                if (card instanceof EYBCard) {
                    ((EYBCard) card).SetHaste(true);
                }

                break;
            }
        }
    }
}