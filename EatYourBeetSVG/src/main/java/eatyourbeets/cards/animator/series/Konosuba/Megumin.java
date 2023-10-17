package eatyourbeets.cards.animator.series.Konosuba;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.special.Megumin_Explosion;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.effects.SFX;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Megumin extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Megumin.class)
            .SetSkill(X_COST, CardRarity.RARE, EYBCardTarget.None)
            
            .SetSeriesFromClassPackage()
            .PostInitialize(data ->
            {
                data.AddPreview(new Megumin_Explosion(), true);
            });

    public Megumin()
    {
        super(DATA);

        Initialize(0, 0, 0);

        SetAffinity_Blue(1);
        SetAffinity_Red(1);

        SetExhaust(true);
        SetUnique(true, true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);

        final int charge = GameUtilities.UseXCostEnergy(this);
        final Megumin_Explosion card = new Megumin_Explosion();

        for (int i=0; i<timesUpgraded; i++) {
            card.upgrade();
        }

        for (int i = 0; i < charge; i++)
        {
            card.upgrade();
        }

        GameActions.Bottom.SFX(SFX.ANIMATOR_MEGUMIN_CHARGE, 0.95f, 1.05f);
        GameActions.Bottom.MakeCardInDrawPile(card)
                .AddCallback(c -> {
                        if (c instanceof Megumin_Explosion) {
                            ((Megumin_Explosion) c).setLinkedUUID(uuid);
                        }
                });
    }
}