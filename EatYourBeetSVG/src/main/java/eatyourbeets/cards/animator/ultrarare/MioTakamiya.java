package eatyourbeets.cards.animator.ultrarare;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.series.DateALive.ShidoItsuka;
import eatyourbeets.cards.base.*;
import eatyourbeets.cards.base.modifiers.CostModifiers;
import eatyourbeets.stances.TranceStance;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class MioTakamiya extends AnimatorCard_UltraRare
{
    public static final EYBCardData DATA = Register(MioTakamiya.class)
            .SetSkill(3, CardRarity.SPECIAL, EYBCardTarget.None)
            .SetColor(CardColor.COLORLESS)
            .SetSeries(CardSeries.DateALive)
            .PostInitialize(data -> data.AddPreview(new ShidoItsuka(), false));

    public MioTakamiya()
    {
        super(DATA);

        Initialize(0, 19, 3, 3);
        SetUpgrade(0, 8, 1);

        SetAffinity_White(2, 0, 2);
        SetAffinity_Blue(1, 0, 1);
    }

    @Override
    public void triggerWhenCreated(boolean startOfBattle)
    {
        super.triggerWhenCreated(startOfBattle);

        if (startOfBattle)
        {
            GameActions.Bottom.ChangeStance(TranceStance.STANCE_ID);
            GameActions.Bottom.GainBlur(magicNumber);

            for (int i=0; i<secondaryValue; i++) {
                GameActions.Bottom.MakeCardInDrawPile(new ShidoItsuka())
                        .SetUpgrade(upgraded, true)
                        .AddCallback(card ->
                        {
                            CostModifiers.For(card).Add(-1);
                        });
            }
        }
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.GainBlock(block);

        if (TranceStance.IsActive()) {
            GameActions.Bottom.GainGreen(block / 2);
        }
    }
}