package eatyourbeets.cards.animator.ultrarare;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.LockOnPower;
import eatyourbeets.cards.animator.series.BlueArchive.HoshinoTakanashi;
import eatyourbeets.cards.base.*;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.cards.base.attributes.TempHPAttribute;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.relics.animator.CursedGlyph;
import eatyourbeets.relics.animator.IronHorus;
import eatyourbeets.ui.common.EYBCardPopupActions;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class YumeKuchinashi extends AnimatorCard_UltraRare {
    public static final EYBCardData DATA = Register(YumeKuchinashi.class)
            .SetSkill(3, CardRarity.SPECIAL, EYBCardTarget.None)
            .SetColor(CardColor.COLORLESS)
            .SetSeries(CardSeries.BlueArchive)
            .PostInitialize(data ->
            {
                data.AddPopupAction(new EYBCardPopupActions.YumeKuchinashi_Death(HoshinoTakanashi.DATA, new CursedGlyph(), 50, new IronHorus()));
                data.AddPreview(new HoshinoTakanashi(), true);
            });

    public YumeKuchinashi() {
        super(DATA);

        Initialize(0, 0, 20, 3);
        SetUpgrade(0, 0, 8, 1);

        SetAffinity_White(2);
        SetAffinity_Teal(2);
    }

    @Override
    public AbstractAttribute GetSpecialInfo()
    {
        return TempHPAttribute.Instance.SetCard(this, true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.Callback(() -> {
            CombatStats.SetMaxHPThreshold(999);
        });

        int numTimesGainTHP = 1;

        for (AbstractMonster enemy : GameUtilities.GetEnemies(true)) {
            if (enemy.hasPower(LockOnPower.POWER_ID)) {
                numTimesGainTHP += secondaryValue;
                break;
            }
        }

        for (int i=0; i<numTimesGainTHP; i++) {
            GameActions.Bottom.GainTemporaryHP(magicNumber);
        }
    }


}