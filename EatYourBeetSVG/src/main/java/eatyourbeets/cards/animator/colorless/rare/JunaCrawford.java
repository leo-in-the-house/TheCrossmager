package eatyourbeets.cards.animator.colorless.rare;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.special.*;
import eatyourbeets.cards.base.*;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.RandomizedList;

public class JunaCrawford extends AnimatorCard {
    public static final EYBCardData DATA = Register(JunaCrawford.class)
            .SetAttack(2, CardRarity.RARE, EYBAttackType.Ranged, EYBCardTarget.Normal)
            .SetColor(CardColor.COLORLESS)
            .SetSeries(CardSeries.LegendOfHeroesTrails)
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

    public JunaCrawford() {
        super(DATA);

        Initialize(3, 3, 0);
        SetUpgrade(1, 1, 0);

        SetRetain(true);

        SetAffinity_Red(1, 0, 1);
        SetAffinity_Brown(1, 0, 1);
    }

    @Override
    public void onRetained()
    {
        super.onRetained();

        AbstractCard card = GetSSSMembers().Retrieve(rng);

        if (card != null) {
            GameActions.Bottom.MakeCardInHand(card.makeCopy())
                    .SetUpgrade(upgraded, true);
        }

        GameActions.Top.IncreaseScaling(this, Affinity.Red, 1);
        GameActions.Top.IncreaseScaling(this, Affinity.Brown, 1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.DealDamage(this, m, AttackEffects.GUNSHOT);
        GameActions.Bottom.GainBlock(block);
    }

    private RandomizedList<AbstractCard> GetSSSMembers() {
        RandomizedList<AbstractCard> list = new RandomizedList<AbstractCard>();

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