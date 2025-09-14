package eatyourbeets.cards.animator.ultrarare;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.InstantKillAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import eatyourbeets.cards.base.*;
import eatyourbeets.effects.VFX;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Grandmaster_Phase4 extends AnimatorCard_UltraRare {
    public static final EYBCardData DATA = Register(Grandmaster_Phase4.class)
            .SetSkill(0, CardRarity.SPECIAL, EYBCardTarget.None)
            .SetColor(CardColor.COLORLESS)
            .SetSeries(CardSeries.LegendOfHeroesTrails);

    public Grandmaster_Phase4() {
        super(DATA);

        Initialize(0, 0, 0);
        SetUpgrade(0, 0, 0);

        SetAffinity_Star(1);
        SetPurge(true);
    }

    @Override
    protected void OnUpgrade()
    {
        super.OnUpgrade();

        SetHaste(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.BorderLongFlash(Color.BLUE);
        GameActions.Bottom.VFX(VFX.Cataclysm());

        AbstractDungeon.getCurrRoom().addRelicToRewards(AbstractRelic.RelicTier.BOSS);

        for (AbstractMonster enemy : GameUtilities.GetEnemies(true)) {
            GameActions.Bottom.Add(new InstantKillAction(enemy));
        }
    }
}