package eatyourbeets.cards.animator.series.LegendOfHeroesTrails;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.special.*;
import eatyourbeets.cards.base.*;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.RandomizedList;

public class LloydBannings extends AnimatorCard {
    public static final EYBCardData DATA = Register(LloydBannings.class)
            .SetAttack(1, CardRarity.UNCOMMON, EYBAttackType.Normal, EYBCardTarget.Normal)
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

        SetRetain(true);
    }

    @Override
    protected void OnUpgrade() {
        super.OnUpgrade();

        SetLoyal(true);
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

        RandomizedList<AbstractCard> SSSMembers = GetSSSMembers();
        AbstractCard member = SSSMembers.Retrieve(rng);

        if (member != null) {
            GameActions.Bottom.MakeCardInHand(member)
                    .SetUpgrade(upgraded, true);
            GameActions.Bottom.ModifyAllCopies(cardID)
                    .AddCallback(c ->
                    {
                        GameUtilities.ModifyDamage(this, secondaryValue, false);
                        GameUtilities.ModifyBlock(this, secondaryValue, false);
                    });
        }
    }

    private RandomizedList<AbstractCard> GetSSSMembers() {
        RandomizedList list = new RandomizedList();

        list.Add(new SSS_Elie());
        list.Add(new SSS_Tio());
        list.Add(new SSS_Randy());
        list.Add(new SSS_Noel());
        list.Add(new SSS_Wazy());
        list.Add(new SSS_Rixia());
        list.Add(new SSS_KeA());

        return list;
    }
}