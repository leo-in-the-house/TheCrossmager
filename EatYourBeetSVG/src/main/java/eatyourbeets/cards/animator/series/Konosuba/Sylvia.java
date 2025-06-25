package eatyourbeets.cards.animator.series.Konosuba;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.special.Sylvia_Chimera;
import eatyourbeets.cards.base.*;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.resources.GR;
import eatyourbeets.ui.common.EYBCardPopupActions;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.TargetHelper;

public class Sylvia extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Sylvia.class)
            .SetAttack(2, CardRarity.UNCOMMON, EYBAttackType.Normal, EYBCardTarget.ALL)
            .SetSeriesFromClassPackage()
            .PostInitialize(data ->
            {
                data.AddPopupAction(new EYBCardPopupActions.Konosuba_Sylvia(8, Hans.DATA, Verdia.DATA, Sylvia_Chimera.DATA));
                data.AddPreview(new Sylvia_Chimera(), true);
            })
            .ModifyRewards((data, rewards) ->
            {
                if (Verdia.DATA.GetTotalCopies(player.masterDeck) <= 0)
                {
                    GR.Common.Dungeon.TryReplaceFirstCardReward(rewards, 0.1f, false, Verdia.DATA);
                }
                else if (Hans.DATA.GetTotalCopies(player.masterDeck) <= 0)
                {
                    GR.Common.Dungeon.TryReplaceFirstCardReward(rewards, 0.1f, false, Hans.DATA);
                }
            });

    public Sylvia()
    {
        super(DATA);

        Initialize(5, 0, 2, 3);
        SetUpgrade(3, 0, 0, 0);

        SetAffinity_Pink(1, 0, 1);
        SetAffinity_Green(1, 0, 1);
    }

    @Override
    public AbstractAttribute GetDamageInfo()
    {
        return super.GetDamageInfo().AddMultiplier(magicNumber);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);

        for (int i=0; i<magicNumber; i++) {
            GameActions.Bottom.DealDamageToAll(this, AttackEffects.SLASH_DIAGONAL);
        }

        GameActions.Bottom.SelectFromHand(name, 1, false)
            .SetMessage(GR.Common.Strings.HandSelection.Seal)
            .AddCallback(cards ->
            {
                for (AbstractCard card : cards)
                {
                    int totalAffinity = 0;

                    for (EYBCardAffinity affinity : GameUtilities.GetAffinities(card).List) {
                        if (affinity.type != Affinity.Sealed) {
                            totalAffinity += affinity.level;
                        }
                    }

                    if (totalAffinity > 0) {
                        GameActions.Top.ApplyConstricted(TargetHelper.RandomEnemy(), totalAffinity);
                    }
                    GameActions.Top.SealAffinities(card);
                }
            });
    }
}