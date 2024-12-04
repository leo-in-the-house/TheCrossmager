package eatyourbeets.cards.animator.series.MadokaMagica;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.watcher.PressEndTurnButtonAction;
import com.megacrit.cardcrawl.actions.watcher.SkipEnemiesTurnAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.TimeWarpTurnEndEffect;
import eatyourbeets.actions.animator.CreateRandomCurses;
import eatyourbeets.cards.animator.curse.special.Curse_GriefSeed;
import eatyourbeets.cards.animator.special.HomuraAkemi_Homulilly;
import eatyourbeets.cards.animator.tokens.AffinityToken;
import eatyourbeets.cards.base.*;
import eatyourbeets.ui.common.EYBCardPopupActions;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class HomuraAkemi extends AnimatorCard
{
    public static final EYBCardData DATA = Register(HomuraAkemi.class)
            .SetSkill(2, CardRarity.RARE, EYBCardTarget.None)
            .SetSeriesFromClassPackage()
            .PostInitialize(data ->
            {
                data.AddPopupAction(new EYBCardPopupActions.MadokaMagica_Witch(HomuraAkemi_Homulilly.DATA));
                data.AddPreview(new HomuraAkemi_Homulilly(), true);
                data.AddPreview(new Curse_GriefSeed(), false);
                data.AddPreview(AffinityToken.GetCard(Affinity.Blue), false);
            });

    public HomuraAkemi()
    {
        super(DATA);

        Initialize(0, 0, 3);
        SetUpgrade(0, 0, 0);

        SetAffinity_Teal(1, 1, 0);
        SetAffinity_Black(1, 1, 0);

        SetExhaust(true);
        SetDelayed(true);
    }

    @Override
    protected void OnUpgrade()
    {
        super.OnUpgrade();

        SetRetain(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.VFX(new TimeWarpTurnEndEffect());
        GameActions.Bottom.VFX(new BorderFlashEffect(Color.VIOLET, true));

        for (int i=0; i<magicNumber; i++) {
            GameActions.Bottom.MakeCardInDiscardPile(CreateRandomCurses.GetRandomCurse(AbstractDungeon.cardRng));
        }

        GameActions.Bottom.Add(new SkipEnemiesTurnAction());
        GameActions.Last.Add(new PressEndTurnButtonAction());
    }
}
