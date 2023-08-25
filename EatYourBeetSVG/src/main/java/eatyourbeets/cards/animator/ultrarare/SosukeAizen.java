package eatyourbeets.cards.animator.ultrarare;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;
import eatyourbeets.cards.base.*;
import eatyourbeets.stances.WrathStance;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class SosukeAizen extends AnimatorCard_UltraRare
{
    public static final EYBCardData DATA = Register(SosukeAizen.class).SetSkill(-1, CardRarity.SPECIAL, EYBCardTarget.None).SetColor(CardColor.COLORLESS).SetSeries(CardSeries.Bleach);
    public SosukeAizen()
    {
        super(DATA);

        Initialize(0, 0, 0);
        SetUpgrade(0,0,0);

        SetAffinity_Dark(2, 0, 0);
        SetAffinity_Red(1, 0, 0);

        SetMultiDamage(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        int energy = GameUtilities.UseXCostEnergy(this);

        if (energy > 0)
        {
            if (WrathStance.IsActive()) {
                GameActions.Bottom.StackPower(new IntangiblePlayerPower(player, energy));
            }
            else {
                GameActions.Bottom.ChangeStance(WrathStance.STANCE_ID);
                GameActions.Bottom.GainStrength(energy);
                GameActions.Last.MoveCard(this, player.drawPile).ShowEffect(true, true);
            }
        }
    }
}