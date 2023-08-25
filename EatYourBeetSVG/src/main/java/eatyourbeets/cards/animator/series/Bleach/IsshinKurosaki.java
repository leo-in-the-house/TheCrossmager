package eatyourbeets.cards.animator.series.Bleach;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.actions.animator.ApplyAmountToOrbs;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.orbs.animator.Fire;
import eatyourbeets.stances.ForceStance;
import eatyourbeets.utilities.GameActions;

public class IsshinKurosaki extends AnimatorCard
{
    public static final EYBCardData DATA = Register(IsshinKurosaki.class).SetSkill(2, CardRarity.UNCOMMON, EYBCardTarget.None);

    public IsshinKurosaki()
    {
        super(DATA);

        Initialize(0, 10, 1, 1);
        SetUpgrade(0, 4, 1);
        
        SetAffinity_Red(2);
        
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameActions.Bottom.GainBlock(block);
    }

    @Override
    public void OnLateUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameActions.Bottom.GainBlock(block);
        GameActions.Bottom.ChannelOrbs(Fire::new, magicNumber);

        if (ForceStance.IsActive()){
            GameActions.Last.Callback(cards -> {
                GameActions.Bottom.Add(new ApplyAmountToOrbs(Fire.ORB_ID, secondaryValue));
            });
        }
    }
}