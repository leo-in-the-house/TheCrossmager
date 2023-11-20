package eatyourbeets.cards.animator.colorless.rare;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.InvisiblePower;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import eatyourbeets.cards.base.*;
import eatyourbeets.stances.MagicStance;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

import java.util.ArrayList;

public class YukariYakumo extends AnimatorCard
{
    public static final EYBCardData DATA = Register(YukariYakumo.class)
            .SetSkill(3, CardRarity.RARE, EYBCardTarget.None)
            .SetColor(CardColor.COLORLESS)
            .SetSeries(CardSeries.TouhouProject);

    public YukariYakumo()
    {
        super(DATA);

        Initialize(0, 0);
        SetCostUpgrade(-1);

        SetAffinity_White(2);
        SetAffinity_Black(2);

        SetLoyal(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Delayed.Callback(() ->
        {
            final ArrayList<AbstractPower> debuffs = new ArrayList<>();
            for (AbstractPower power : player.powers)
            {
                if (power.type == AbstractPower.PowerType.DEBUFF && !(power instanceof InvisiblePower))
                {
                    debuffs.add(power);
                }
            }

            if (debuffs.size() > 0 && player.powers.removeAll(debuffs))
            {
                for (AbstractPower debuff : debuffs)
                {
                    GameActions.Top.GainStrength(1);
                    GameActions.Top.GainDexterity(1);
                    GameActions.Top.GainFocus(1);
                }
            }
        });
        GameActions.Bottom.ChangeStance(MagicStance.STANCE_ID);
    }
}

