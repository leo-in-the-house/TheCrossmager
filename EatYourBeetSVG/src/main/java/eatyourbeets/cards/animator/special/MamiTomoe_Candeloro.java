package eatyourbeets.cards.animator.special;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.stances.NeutralStance;
import eatyourbeets.cards.animator.series.MadokaMagica.MamiTomoe;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.stances.CalmStance;
import eatyourbeets.utilities.GameActions;

public class MamiTomoe_Candeloro extends AnimatorCard
{
    public static final EYBCardData DATA = Register(MamiTomoe_Candeloro.class)
            .SetSkill(3, CardRarity.SPECIAL, EYBCardTarget.None)
            .SetSeries(MamiTomoe.DATA.Series);

    public MamiTomoe_Candeloro()
    {
        super(DATA);

        Initialize(0, 0, 4);
        SetUpgrade(0, 0, 3);

        SetAffinity_Yellow(2);
        SetAffinity_Black(1);

        SetExhaust(true);
        SetRetain(true);
    }

    @Override
    protected void OnUpgrade()
    {
        super.OnUpgrade();

        SetInnate(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameActions.Bottom.PurgeFromPile(name, magicNumber, player.exhaustPile)
            .SetFilter(card -> card.type == CardType.CURSE)
            .SetOptions(true, true)
            .AddCallback(cards -> {
                int numCardsPurged = cards.size();

                for (int i=0; i<numCardsPurged; i++) {
                    GameActions.Top.ChangeStance(NeutralStance.STANCE_ID);
                    GameActions.Top.ChangeStance(CalmStance.STANCE_ID);
                }
            });
    }
}