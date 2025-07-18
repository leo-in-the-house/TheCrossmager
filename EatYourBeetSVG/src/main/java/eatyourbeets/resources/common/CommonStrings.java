package eatyourbeets.resources.common;

import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.actions.unique.GamblingChipAction;
import com.megacrit.cardcrawl.actions.unique.RetainCardsAction;
import com.megacrit.cardcrawl.actions.watcher.OmniscienceAction;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.screens.CardRewardScreen;
import com.megacrit.cardcrawl.screens.charSelect.CharacterSelectScreen;
import eatyourbeets.resources.GR;
import eatyourbeets.utilities.JUtils;

public class CommonStrings
{
    public TheUnnamedReign TheUnnamedReign;
    public HandSelection HandSelection;
    public GridSelection GridSelection;
    public Ascension Ascension;
    public Terms Terms;

    public void Initialize()
    {
        TheUnnamedReign = new TheUnnamedReign();
        HandSelection = new HandSelection();
        GridSelection = new GridSelection();
        Ascension = new Ascension();
        Terms = new Terms();
    }

    public class TheUnnamedReign
    {
        public final UIStrings Strings = GetUIStrings("TheUnnamedReign");
        public final String Name = Strings.TEXT[0];
    }

    public class HandSelection
    {
        public final UIStrings Strings = GetUIStrings("HandSelection");
        public final String MoveToDrawPile = Strings.TEXT[0];
        public final String GenericBuff = Strings.TEXT[1];
        public final String Copy = Strings.TEXT[2];
        public final String Activate = Strings.TEXT[3];
        public final String Seal = Strings.TEXT[4];
        public final String Discard = DiscardAction.TEXT[0];
        public final String Exhaust = ExhaustAction.TEXT[0];
        public final String Obtain = CardRewardScreen.TEXT[1];
        public final String Retain = RetainCardsAction.TEXT[0];
    }

    public class GridSelection
    {
        public final UIStrings Strings = GetUIStrings("GridSelection");
        public final String DiscardUpTo_F1 = Strings.TEXT[0];
        public final String MoveToDrawPile_F1 = Strings.TEXT[1];
        public final String TransformInto_F1 = Strings.TEXT[2];
        public final String ChooseCards_F1 = Strings.TEXT[3];
        public final String Purge_F1 = Strings.TEXT[4];
        public final String Scry = Strings.TEXT[5];
        public final String Fetch_F1 = Strings.TEXT[6];
        public final String PermanentlyRemove = Strings.TEXT[7];
        public final String PermanentlyAdd = Strings.TEXT[8];
        public final String Discard = DiscardAction.TEXT[0];
        public final String Exhaust = ExhaustAction.TEXT[0];
        public final String Cycle = GamblingChipAction.TEXT[1];
        public final String ChooseOneCard = CardRewardScreen.TEXT[1];
        public final String ChooseAndPlay = OmniscienceAction.TEXT[0];

        public final String DiscardUpTo(int amount)
        {
            return JUtils.Format(DiscardUpTo_F1, amount);
        }

        public final String MoveToDrawPile(int amount)
        {
            return JUtils.Format(MoveToDrawPile_F1, amount);
        }

        public final String TransformInto(String name)
        {
            return JUtils.Format(TransformInto_F1, name);
        }

        public final String ChooseCards(int amount)
        {
            return JUtils.Format(ChooseCards_F1, amount);
        }

        public final String Purge(int amount)
        {
            return JUtils.Format(Purge_F1, amount);
        }

        public final String Fetch(int amount)
        {
            return JUtils.Format(Fetch_F1, amount);
        }
    }

    public class Ascension
    {
        public final UIStrings Strings = GetUIStrings("Ascension");
        public final String Title = Strings.TEXT[0];

        public String Title(Object level)
        {
            return Title + " " + level;
        }

        public String GetDescription(int ascension)
        {
            if (ascension < 20)
            {
                return CharacterSelectScreen.A_TEXT[ascension];
            }
            else
            {
                return Strings.TEXT[1 + ascension - 20];
            }
        }
    }

    public class Terms
    {
        public final UIStrings Strings = GetUIStrings("Terms");
        public final String Ascension = Strings.TEXT[0];
        public final String Transform = Strings.TEXT[1];
        public final String Upgrade = Strings.TEXT[2];
        public final String Downgrade = Strings.TEXT[3];
        public final String Improve = Strings.TEXT[4];
        public final String Obtain = Strings.TEXT[5];
        public final String Attack = Strings.TEXT[6];
        public final String Defend = Strings.TEXT[7];
        public final String Random = Strings.TEXT[8];
    }

    private static UIStrings GetUIStrings(String id)
    {
        String fullID = GR.CreateID(CommonResources.ID, id);

        return GR.GetUIStrings(fullID);
    }
}
