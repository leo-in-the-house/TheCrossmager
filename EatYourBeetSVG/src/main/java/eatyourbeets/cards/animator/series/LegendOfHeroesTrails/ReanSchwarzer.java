package eatyourbeets.cards.animator.series.LegendOfHeroesTrails;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.Lightning;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import eatyourbeets.cards.base.*;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class ReanSchwarzer extends AnimatorCard {
    public static final EYBCardData DATA = Register(ReanSchwarzer.class)
            .SetAttack(4, CardRarity.RARE, EYBAttackType.Normal, EYBCardTarget.Normal)
            .SetSeriesFromClassPackage();

    private boolean AlreadyTriggered;

    public ReanSchwarzer() {
        super(DATA);

        Initialize(4, 0, 8);
        SetUpgrade(1, 3, 0);
        SetUnique(true, true);

        SetAffinity_Red(2);
        SetAffinity_Black(2);
    }

    @Override
    protected void OnUpgrade()
    {
        super.OnUpgrade();

        if (timesUpgraded >= 6) {
            LoadImage("_Upgraded3");
            SetInnate(true);
            SetAutoplayed(false);
            SetAttackTarget(EYBCardTarget.ALL);
            AddScaling(Affinity.Red, 6);
            AddScaling(Affinity.Black, 6);
            SetMultiDamage(true);
        }
        else if (timesUpgraded >= 3) {
            LoadImage("_Upgraded2");
            SetAutoplayed(true);
            AddScaling(Affinity.Red, 2);
            AddScaling(Affinity.Black, 2);
        }
        else if (timesUpgraded >= 1)
        {
            LoadImage("_Upgraded");
            AddScaling(Affinity.Red, 1);
            AddScaling(Affinity.Black, 1);
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

        GameActions.Bottom.GainBlock(block);

        GameActions.Bottom.VFX(new BorderFlashEffect(Color.RED));
        GameActions.Bottom.Wait(0.2f);

        for (int i=0; i<magicNumber; i++) {
            if (timesUpgraded >= 6) {
                if (i%3 == 0) {
                    GameActions.Bottom.DealDamageToAll(this, AttackEffects.SLASH_VERTICAL)
                        .AddCallback(info, (info2, enemies) ->
                        {
                            for (AbstractCreature enemy : enemies) {
                                if (GameUtilities.IsFatal(enemy, true)) {
                                    UpgradeCard();
                                }
                            }
                        });
                }
                else if (i%3 == 1) {
                    GameActions.Bottom.DealDamageToAll(this, AttackEffects.SLASH_HORIZONTAL)
                        .AddCallback(info, (info2, enemies) ->
                        {
                            for (AbstractCreature enemy : enemies) {
                                if (GameUtilities.IsFatal(enemy, true)) {
                                    UpgradeCard();
                                }
                            }
                        });
                }
                else {
                    GameActions.Bottom.DealDamageToAll(this, AttackEffects.SLASH_HEAVY)
                        .AddCallback(info, (info2, enemies) ->
                        {
                            for (AbstractCreature enemy : enemies) {
                                if (GameUtilities.IsFatal(enemy, true)) {
                                    UpgradeCard();
                                }
                            }
                        });
                }
            }
            else {
                if (i%3 == 0) {
                    GameActions.Bottom.DealDamage(this, m, AttackEffects.SLASH_VERTICAL)
                        .AddCallback(info, (info2, enemy) ->
                        {
                            if (GameUtilities.IsFatal(enemy, true)) {
                                UpgradeCard();
                            }
                        });
                }
                else if (i%3 == 1) {
                    GameActions.Bottom.DealDamage(this, m, AttackEffects.SLASH_HORIZONTAL)
                        .AddCallback(info, (info2, enemy) ->
                        {
                            if (GameUtilities.IsFatal(enemy, true)) {
                                UpgradeCard();
                            }
                        });
                }
                else {
                    GameActions.Bottom.DealDamage(this, m, AttackEffects.SLASH_HEAVY)
                        .AddCallback(info, (info2, enemy) ->
                        {
                            if (GameUtilities.IsFatal(enemy, true)) {
                                UpgradeCard();
                            }
                        });
                }
            }
            GameActions.Bottom.Wait(0.2f);
        }

        GameActions.Last.Callback(() -> {
            AlreadyTriggered = false;
        });
    }

    private void UpgradeCard() {
        if (!AlreadyTriggered) {
            if (CombatStats.TryActivateLimited(cardID)) {
                int numTimesUpgrade = 1;

                if (GameUtilities.InEliteOrBossRoom()) {
                    numTimesUpgrade++;
                }

                if (GameUtilities.GetOrbCount(Lightning.ORB_ID) >= 3) {
                    numTimesUpgrade++;
                }

                for (int i=0; i<numTimesUpgrade; i++) {
                    GameActions.Bottom.ModifyAllInstances(uuid, AbstractCard::upgrade)
                            .IncludeMasterDeck(true)
                            .IsCancellable(false);
                }
            }

            AlreadyTriggered = true;
        }
    }
}