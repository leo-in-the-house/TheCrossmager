package eatyourbeets.cards.animator.special;

import com.evacipated.cardcrawl.mod.stslib.powers.StunMonsterPower;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.DieDieDieEffect;
import eatyourbeets.cards.base.*;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Ellen_Viola extends AnimatorCard {
    public static final EYBCardData DATA = Register(Ellen_Viola.class)
            .SetSkill(1, CardRarity.SPECIAL, EYBCardTarget.None)
            .SetSeries(CardSeries.TheWitchsHouse);

    public Ellen_Viola() {
        super(DATA);

        Initialize(0, 8, 0);
        SetUpgrade(0, 0, 0);

        SetAffinity_Violet(2, 0, 3);
        SetAffinity_Yellow(1, 0, 3);

        SetHaste(true);
        SetRetain(true);
    }

    @Override
    protected void OnUpgrade()
    {
        super.OnUpgrade();

        SetInnate(true);
        SetLoyal(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.GainBlock(block);

        GameActions.Bottom.ExhaustFromPile(name, 999, p.hand, p.discardPile)
                .ShowEffect(true, true)
                .SetOptions(true, true)
                .SetFilter(c -> c.rarity == CardRarity.COMMON || c.rarity == CardRarity.BASIC)
                .AddCallback(cards -> {
                    int numExhausted = cards.size();

                    if (numExhausted >= 3) {
                        GameActions.Bottom.ShakeScreen(0.5f, ScreenShake.ShakeDur.MED, ScreenShake.ShakeIntensity.MED);
                        GameActions.Bottom.VFX(new DieDieDieEffect());

                        for (AbstractMonster enemy : GameUtilities.GetEnemies(true)) {
                            GameActions.Bottom.ApplyPower(p, enemy, new StunMonsterPower(enemy, 1));
                        }

                        GameActions.Bottom.ApplyPower(new EllenPower(player, 1));
                    }
                });
    }

    public static class EllenPower extends AnimatorPower
    {
        public EllenPower(AbstractCreature owner, int amount)
        {
            super(owner, Ellen_Viola.DATA);

            this.amount = amount;

            updateDescription();
        }

        @Override
        public void atStartOfTurnPostDraw() {
            super.atStartOfTurnPostDraw();

            GameActions.Bottom.GainEnergy(amount);
        }

        @Override
        public void updateDescription()
        {
            description = FormatDescription(0, amount);
        }
    }
}