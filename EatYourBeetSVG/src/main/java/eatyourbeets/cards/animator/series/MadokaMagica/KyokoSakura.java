package eatyourbeets.cards.animator.series.MadokaMagica;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.curse.special.Curse_GriefSeed;
import eatyourbeets.cards.animator.special.KyokoSakura_Ophelia;
import eatyourbeets.cards.base.*;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.ui.common.EYBCardPopupActions;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.TargetHelper;

public class KyokoSakura extends AnimatorCard
{
    public static final EYBCardData DATA = Register(KyokoSakura.class)
            .SetAttack(2, CardRarity.UNCOMMON, EYBAttackType.Piercing, EYBCardTarget.ALL)
            .SetSeriesFromClassPackage()
            .PostInitialize(data ->
            {
                data.AddPopupAction(new EYBCardPopupActions.MadokaMagica_Witch(KyokoSakura_Ophelia.DATA));
                data.AddPreview(new KyokoSakura_Ophelia(), true);
                data.AddPreview(new Curse_GriefSeed(), false);
            });

    public KyokoSakura()
    {
        super(DATA);

        Initialize(6, 0, 3, 2);
        SetUpgrade(2, 0, 2);

        SetAffinity_Red(1, 0, 1);
        SetAffinity_Pink(1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.DealDamageToAll(this, AttackEffects.SPEAR).SetVFXColor(Color.RED);

        for (AbstractMonster enemy : GameUtilities.GetEnemies(true)) {
            GameActions.Bottom.ReduceStrength(enemy, magicNumber, true);
        }

        if (info.TryActivateStarter()) {
            GameActions.Bottom.ApplyLockOn(TargetHelper.RandomEnemy(), secondaryValue);
        }
    }
}
