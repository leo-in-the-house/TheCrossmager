package eatyourbeets.cards.animator.series.Katanagatari;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.special.Shichika_Kyotouryuu;
import eatyourbeets.cards.base.*;
import eatyourbeets.cards.effects.GenericEffects.GenericEffect;
import eatyourbeets.stances.TranceStance;
import eatyourbeets.stances.WrathStance;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.JUtils;

public class Shichika extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Shichika.class)
            .SetSkill(1, CardRarity.UNCOMMON, EYBCardTarget.None)
            .SetSeriesFromClassPackage()
            .PostInitialize(data -> data.AddPreview(new Shichika_Kyotouryuu(), true));

    private static final CardEffectChoice choices = new CardEffectChoice();

    public Shichika()
    {
        super(DATA);

        Initialize(0, 0);

        SetAffinity_Red(1, 1, 0);
        SetAffinity_Green(1, 1, 0);

        SetDelayed(true);
        SetExhaust(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.GainBlock(block);
        choices.Initialize(this, true);
        choices.AddEffect(new GenericEffect_Force(this));
        choices.AddEffect(new GenericEffect_Agility(this));
        choices.Select(1, m);
    }

    protected static class GenericEffect_Force extends GenericEffect
    {
        private final AbstractCard kyotouryuu;

        public GenericEffect_Force(Shichika shichika)
        {
            this.kyotouryuu = Shichika_Kyotouryuu.DATA.CreateNewInstance(false);
        }

        @Override
        public String GetText()
        {
            return JUtils.Format(Shichika.DATA.Strings.EXTENDED_DESCRIPTION[0], kyotouryuu.name);
        }

        @Override
        public void Use(EYBCard card, AbstractPlayer p, AbstractMonster m)
        {
            GameActions.Bottom.ChangeStance(WrathStance.STANCE_ID);
            GameActions.Bottom.MakeCardInHand(kyotouryuu);
        }
    }

    protected static class GenericEffect_Agility extends GenericEffect
    {
        private final AbstractCard shichika;

        public GenericEffect_Agility(Shichika shichika)
        {
            this.shichika = shichika;
        }

        @Override
        public String GetText()
        {
            return JUtils.Format(Shichika.DATA.Strings.EXTENDED_DESCRIPTION[1], shichika.magicNumber);
        }

        @Override
        public void Use(EYBCard card, AbstractPlayer p, AbstractMonster m)
        {
            GameActions.Bottom.ChangeStance(TranceStance.STANCE_ID);
            GameActions.Bottom.GainBurst(1);
        }
    }
}