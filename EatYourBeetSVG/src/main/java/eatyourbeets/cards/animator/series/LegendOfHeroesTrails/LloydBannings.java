package eatyourbeets.cards.animator.series.LegendOfHeroesTrails;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.Lightning;
import eatyourbeets.cards.animator.special.*;
import eatyourbeets.cards.base.*;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class LloydBannings extends AnimatorCard {
    public static final EYBCardData DATA = Register(LloydBannings.class)
            .SetAttack(2, CardRarity.UNCOMMON, EYBAttackType.Normal, EYBCardTarget.Normal)
            .SetSeriesFromClassPackage()
            .PostInitialize(data ->
            {
                data.AddPreview(new SSS_Elie(), true);
                data.AddPreview(new SSS_Tio(), true);
                data.AddPreview(new SSS_Randy(), true);
                data.AddPreview(new SSS_Noel(), true);
                data.AddPreview(new SSS_Wazy(), true);
                data.AddPreview(new SSS_Rixia(), true);
                data.AddPreview(new SSS_KeA(), true);
            });


    public LloydBannings() {
        super(DATA);

        Initialize(2, 0, 2, 2);
        SetUpgrade(0, 0, 0);

        SetAffinity_Brown(1, 0, 1);

        SetEthereal(true);
    }

    @Override
    public AbstractAttribute GetDamageInfo()
    {
        return super.GetDamageInfo().AddMultiplier(magicNumber);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        for (int i=0; i<magicNumber; i++) {
            GameActions.Bottom.DealDamage(this, m, AttackEffects.BLUNT_LIGHT);
            GameActions.Bottom.Wait(0.3f);
        }

        CardGroup group = GetSSSMembers();
        GameActions.Bottom.SelectFromPile(name, 1, group)
        .SetOptions(false, false)
        .AddCallback(cards -> {
            for (AbstractCard member : cards) {
                GameActions.Bottom.MakeCardInDrawPile(member)
                        .SetUpgrade(upgraded, true);
            }
        });

        GameActions.Bottom.ChannelOrb(new Lightning());
    }

    private CardGroup GetSSSMembers() {
        CardGroup list = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);

        list.addToTop(new SSS_Elie());
        list.addToTop(new SSS_Tio());
        list.addToTop(new SSS_Randy());
        list.addToTop(new SSS_Noel());
        list.addToTop(new SSS_Wazy());
        list.addToTop(new SSS_Rixia());
        list.addToTop(new SSS_KeA());

        return list;
    }
}