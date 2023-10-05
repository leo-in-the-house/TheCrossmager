package eatyourbeets.cards.animator.series.GenshinImpact;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.interfaces.subscribers.OnStartOfTurnSubscriber;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameEffects;
import eatyourbeets.utilities.GameUtilities;

public class Freminet extends AnimatorCard implements OnStartOfTurnSubscriber {
    public static final EYBCardData DATA = Register(Freminet.class)
            .SetSkill(2, CardRarity.UNCOMMON, EYBCardTarget.None)
            .SetSeriesFromClassPackage();

    public Freminet() {
        super(DATA);

        Initialize(0, 5, 0);
        SetUpgrade(0, 0, 0);

        SetAffinity_Blue(1, 0, 1);
        SetAffinity_Teal(1, 0, 1);

        SetExhaust(true);
    }

    @Override
    public void OnStartOfTurn()
    {
        if (player.exhaustPile.contains(this) && GameUtilities.GetUniqueOrbsCount() >= 3)
        {
            GameEffects.List.ShowCopy(this, Settings.WIDTH * 0.75f, Settings.HEIGHT * 0.4f);

            if (upgraded) {
                GameActions.Top.GainEnergy(1);
            }

            GameActions.Top.FetchFromPile(name, 1, player.discardPile)
                            .ShowEffect(true, true)
                            .SetOptions(true, true);

            GameActions.Last.MoveCard(this, player.exhaustPile, player.drawPile)
                    .ShowEffect(true, false);
        }
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.GainBlock(block);
    }

    @Override
    public void triggerWhenCreated(boolean startOfBattle)
    {
        super.triggerWhenCreated(startOfBattle);

        CombatStats.onStartOfTurn.Subscribe(this);
    }
}