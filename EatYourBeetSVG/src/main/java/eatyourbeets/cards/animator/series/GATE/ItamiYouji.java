package eatyourbeets.cards.animator.series.GATE;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBAttackType;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class ItamiYouji extends AnimatorCard
{
    public static final EYBCardData DATA = Register(ItamiYouji.class)
            .SetAttack(3, CardRarity.RARE, EYBAttackType.Ranged)
            .SetSeriesFromClassPackage();

    public ItamiYouji()
    {
        super(DATA);

        Initialize(6, 0, 0);
        SetUpgrade(6, 0, 0);

        SetAffinity_Green(2, 0, 1);
        SetAffinity_Red(1, 0, 1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);

        int amountToDraw = Math.max(0, player.gameHandSize - player.hand.size());

        GameActions.Bottom.DealDamage(this, m, AttackEffects.GUNSHOT).SetSoundPitch(1.3f, 1.5f);

        GameActions.Bottom.Draw(amountToDraw)
        .AddCallback(c -> {
            for (AbstractCard card : player.hand.group) {
                if (card.type == CardType.ATTACK) {
                    GameActions.Top.DealDamage(this, m, AttackEffects.GUNSHOT).SetSoundPitch(1.3f, 1.5f);
                    GameUtilities.Retain(card);
                }
            }
        });
    }
}