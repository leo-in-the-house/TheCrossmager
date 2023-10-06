package eatyourbeets.cards.animator.series.AngelBeats;

import com.megacrit.cardcrawl.cards.AbstractCard;
import eatyourbeets.stances.CalmStance;
import eatyourbeets.utilities.GameUtilities;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.utilities.GameActions;

public class YuzuruOtonashi extends AnimatorCard
{
    public static final EYBCardData DATA = Register(YuzuruOtonashi.class).SetSkill(1, CardRarity.UNCOMMON, EYBCardTarget.Self)
            .SetSeriesFromClassPackage();

    public YuzuruOtonashi()
    {
        super(DATA);

        Initialize(0, 3, 0, 0);
        SetUpgrade(0, 4, 0, 0);

        SetAffinity_Pink(1);
        SetAffinity_Brown(1);
    }


    @Override
    public void triggerOnExhaust() {
        super.triggerOnExhaust();

        GameActions.Top.ChangeStance(CalmStance.STANCE_ID);
    }

    @Override
    public void Refresh(AbstractMonster enemy)
    {
        super.Refresh(enemy);

        int numEthereal = 0;

        for (AbstractCard card : player.exhaustPile.group) {
            if (card.isEthereal) {
                numEthereal++;
            }
        }

        int scalingAmount = numEthereal;

        if (scalingAmount > 0) {
            SetScaling(Affinity.Pink, scalingAmount);
            SetScaling(Affinity.Brown, scalingAmount);
        }
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.GainBlock(block);
    }
}