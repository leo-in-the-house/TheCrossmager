package eatyourbeets.cards.animatorClassic.series.Overlord;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorClassicCard;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.monsters.EnemyIntent;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.JUtils;

public class Sebas extends AnimatorClassicCard
{
    public static final EYBCardData DATA = Register(Sebas.class).SetSeriesFromClassPackage().SetSkill(2, CardRarity.UNCOMMON, EYBCardTarget.None);

    public Sebas()
    {
        super(DATA);

        Initialize(0, 6, 3);
        SetUpgrade(0, 3);
        SetScaling(0, 1, 2);

        SetExhaust(true);

    }

    @Override
    public void triggerOnManualDiscard()
    {
        super.triggerOnManualDiscard();

        GameActions.Bottom.GainTemporaryHP(magicNumber);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.GainBlock(block).AddCallback(() ->
        {
            for (EnemyIntent intent : JUtils.Filter(GameUtilities.GetIntents(), i -> i.isAttacking))
            {
                GameActions.Bottom.DealDamage(player, intent.enemy, player.currentBlock, DamageInfo.DamageType.THORNS, AttackEffects.BLUNT_HEAVY);
            }
        });
    }
}