package eatyourbeets.cards.animatorClassic.series.GoblinSlayer;

import com.megacrit.cardcrawl.cards.AbstractCard;
import eatyourbeets.utilities.GameUtilities;
import com.megacrit.cardcrawl.cards.status.Wound;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.stances.NeutralStance;
import eatyourbeets.cards.animator.status.Status_Wound;
import eatyourbeets.cards.base.*;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.cards.effects.GenericEffects.GenericEffect_EnterStance;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.stances.AgilityStance;
import eatyourbeets.stances.WrathStance;
import eatyourbeets.stances.IntellectStance;
import eatyourbeets.utilities.GameActions;

public class Spearman extends AnimatorClassicCard
{
    public static final EYBCardData DATA = Register(Spearman.class).SetSeriesFromClassPackage().SetAttack(1, CardRarity.COMMON, EYBAttackType.Piercing);
    static
    {
        DATA.AddPreview(new Witch(), true);
        DATA.AddPreview(new Status_Wound(), false);
    }

    private static final CardEffectChoice choices = new CardEffectChoice();

    public Spearman()
    {
        super(DATA);

        Initialize(8, 0, 1);
        SetUpgrade(4, 0);
        SetScaling(0, 1, 1);

        
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.DealDamage(this, m, AttackEffects.SLASH_VERTICAL);
        GameActions.Bottom.GainGreen(magicNumber, true);
        GameActions.Bottom.GainRed(magicNumber, true);
        GameActions.Bottom.MakeCardInDrawPile(new Wound());

        if (choices.TryInitialize(this))
        {
            choices.AddEffect(new GenericEffect_EnterStance(WrathStance.STANCE_ID));
            choices.AddEffect(new GenericEffect_EnterStance(AgilityStance.STANCE_ID));
            choices.Initialize(new Witch());
            choices.AddEffect(new GenericEffect_EnterStance(IntellectStance.STANCE_ID));
            choices.AddEffect(new GenericEffect_EnterStance(NeutralStance.STANCE_ID));
            choices.Initialize(this);
        }

        if (!CombatStats.HasActivatedSemiLimited(cardID))
        {
            for (AbstractCard c : p.hand.group)
            {
                if (c.cardID.equals(Witch.DATA.ID) && CombatStats.TryActivateSemiLimited(cardID))
                {
                    choices.Select(1, m);
                    break;
                }
            }
        }
    }
}