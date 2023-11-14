package eatyourbeets.cards.animator.series.RozenMaiden;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.special.Suigintou_BlackFeather;
import eatyourbeets.cards.base.*;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.ListSelection;

public class Suigintou extends AnimatorCard {
    public static final EYBCardData DATA = Register(Suigintou.class)
            .SetAttack(3, CardRarity.RARE, EYBAttackType.Elemental, EYBCardTarget.ALL)
            .SetSeriesFromClassPackage()
            .PostInitialize(data -> data.AddPreview(new Suigintou_BlackFeather(), false));

    public Suigintou() {
        super(DATA);

        Initialize(12, 0, 3);
        SetUpgrade(3, 0, 1);

        SetAffinity_Violet(1, 0, 1);
        SetAffinity_Black(1, 0, 1);
        SetAffinity_Blue(1, 0, 1);

        SetUnique(true, true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.DealDamageToAll(this, AbstractGameAction.AttackEffect.POISON);

        GameActions.Bottom.RemoveDebuffs(player, ListSelection.Last(0), 1)
            .AddCallback(debuffs -> {
                if (debuffs.size() > 0) {
                    Suigintou_BlackFeather blackFeather = new Suigintou_BlackFeather();
                    blackFeather.setLinkedUUID(uuid);
                    GameActions.Top.MakeCardInHand(blackFeather);
                }
            });

    }
}