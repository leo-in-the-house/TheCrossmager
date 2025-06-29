package eatyourbeets.cards.animator.series.BlueArchive;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.LockOnPower;
import eatyourbeets.cards.animator.special.Shiroko_Terror;
import eatyourbeets.cards.base.*;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.ui.common.EYBCardPopupActions;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.TargetHelper;

public class ShirokoSunaookami extends AnimatorCard {
    public static final EYBCardData DATA = Register(ShirokoSunaookami.class)
            .SetAttack(2, CardRarity.UNCOMMON, EYBAttackType.Ranged, EYBCardTarget.Normal)
            .SetSeriesFromClassPackage()
            .PostInitialize(data ->
            {
                data.AddPopupAction(new EYBCardPopupActions.ShirokoSunaookami_Terror(ShirokoSunaookami.DATA, Shiroko_Terror.DATA, 5));
                data.AddPreview(new Shiroko_Terror(), true);
            });

    public ShirokoSunaookami() {
        super(DATA);

        Initialize(10, 0, 2);
        SetUpgrade(8, 0, 0);

        SetAffinity_Teal(1);
        SetAffinity_Green(1);

        SetEthereal(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.DealDamage(this, m, AttackEffects.GUNSHOT)
            .AddCallback(enemy -> {
                if (CombatStats.TryActivateSemiLimited(cardID)) {
                    if (enemy.hasPower(LockOnPower.POWER_ID)) {
                        if (player.discardPile.size() > 0 && enemy instanceof AbstractMonster && !enemy.isDeadOrEscaped()) {
                            AbstractCard card = player.discardPile.getTopCard();

                            if (card != null) {
                                GameActions.Top.PlayCard(card, player.discardPile, (AbstractMonster) enemy);
                            }
                        }
                    }
                    else {
                        GameActions.Top.ApplyLockOn(TargetHelper.Normal(enemy), magicNumber);
                    }
                }
            });
    }
}