package eatyourbeets.cards.effects.GenericEffects;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.EYBCard;
import eatyourbeets.cards.base.EYBCardTooltip;
import eatyourbeets.resources.GR;
import eatyourbeets.stances.EYBStance;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class GenericEffect_EnterStance extends GenericEffect
{
    public GenericEffect_EnterStance(String stanceID)
    {
        this.id = stanceID;
        this.tooltip = EYBStance.GetStanceTooltip(GameUtilities.GetPlayerClass(), stanceID);
    }

    public GenericEffect_EnterStance(String stanceID, EYBCardTooltip tooltip)
    {
        this.id = stanceID;
        this.tooltip = tooltip;
    }

    @Override
    public String GetText()
    {
        String text;

        // TODO: Create a reusable method which replaces all keywords with their icons
        if (GameUtilities.IsPlayerClass(GR.Animator.PlayerClass))
        {
            text = tooltip.title
            .replace(GR.Tooltips.GreenThreshold.title, "[Agility]")
            .replace(GR.Tooltips.RedThreshold.title, "[Force]")
            .replace(GR.Tooltips.BlueThreshold.title, "[Intellect]")
            .replace(GR.Tooltips.BlackThreshold.title, "[Corruption]")
            .replace(GR.Tooltips.WrathStance.title, "[Wrath Stance]")
            .replace(GR.Tooltips.TranceStance.title, "[Trance Stance]")
            .replace(GR.Tooltips.MagicStance.title, "[Magic Stance]")
            .replace(GR.Tooltips.CalmStance.title, "[Calm Stance]")
            .replace(GR.Tooltips.NeutralStance.title, "[Neutral Stance]")
            .replace(GR.Tooltips.DivinityStance.title, "[Divinity Stance]");
        }
        else
        {
            text = tooltip.title
            .replace(GR.Tooltips.GreenThreshold.title, "[A]")
            .replace(GR.Tooltips.RedThreshold.title, "[F]")
            .replace(GR.Tooltips.BlueThreshold.title, "[I]")
            .replace(GR.Tooltips.BlackThreshold.title, "[C]")
            .replace(GR.Tooltips.WrathStance.title, "[Wrath Stance]")
            .replace(GR.Tooltips.TranceStance.title, "[Trance Stance]")
            .replace(GR.Tooltips.MagicStance.title, "[Magic Stance]")
            .replace(GR.Tooltips.CalmStance.title, "[Calm Stance]")
            .replace(GR.Tooltips.DivinityStance.title, "[Divinity Stance]");
        }

        return GR.Animator.Strings.Actions.EnterStance("{" + text + "}", true);
    }

    @Override
    public void Use(EYBCard card, AbstractPlayer p, AbstractMonster m)
    {
        GameActions.Bottom.ChangeStance(id);
    }
}
