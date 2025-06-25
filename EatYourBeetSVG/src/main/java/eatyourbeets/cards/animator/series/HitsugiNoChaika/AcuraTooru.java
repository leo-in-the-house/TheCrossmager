package eatyourbeets.cards.animator.series.HitsugiNoChaika;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.special.AcuraTooru_Dragoon;
import eatyourbeets.cards.animator.special.ThrowingKnife;
import eatyourbeets.cards.base.*;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.effects.VFX;
import eatyourbeets.resources.GR;
import eatyourbeets.ui.common.EYBCardPopupActions;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameEffects;
import eatyourbeets.utilities.GameUtilities;

public class AcuraTooru extends AnimatorCard
{
    public static final EYBCardData DATA = Register(AcuraTooru.class)
        .SetAttack(2, CardRarity.UNCOMMON, EYBAttackType.Normal, EYBCardTarget.Normal)
        .SetSeriesFromClassPackage()
        .PostInitialize(data ->
        {
            data.AddPopupAction(new EYBCardPopupActions.HitsugiNoChaika_Tooru(6, Fredrika.DATA, AcuraTooru_Dragoon.DATA));
            data.AddPreview(new AcuraTooru_Dragoon(), true);
            for (ThrowingKnife knife : ThrowingKnife.GetAllCards())
            {
                data.AddPreview(knife, false);
            }
        })
        .ModifyRewards((data, rewards) ->
        {
            if (Fredrika.DATA.GetTotalCopies(player.masterDeck) <= 0 && ChaikaTrabant.DATA.GetTotalCopies(player.masterDeck) > 0)
            {
                GR.Common.Dungeon.TryReplaceFirstCardReward(rewards, 0.2f, false, Fredrika.DATA);
            }
        });

    public AcuraTooru()
    {
        super(DATA);

        Initialize(4, 0, 2, 3);
        SetUpgrade(2, 0, 0, 2);

        SetAffinity_Green(1, 0, 1);
        SetAffinity_Black(1);
    }

    @Override
    public void triggerOnManualDiscard()
    {
        super.triggerOnManualDiscard();

        GameActions.Top.GainBlock(secondaryValue);
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
            GameActions.Bottom.DealDamage(this, m, AttackEffects.NONE)
               .SetDamageEffect(c -> GameEffects.List.Add(VFX.ThrowDagger(c.hb, 0.15f).SetColor(Color.GREEN)).duration * 0.5f);
        }

        GameActions.Bottom.CreateThrowingKnives(3);
    }
}