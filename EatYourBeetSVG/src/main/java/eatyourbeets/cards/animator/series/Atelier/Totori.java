package eatyourbeets.cards.animator.series.Atelier;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.special.Chim;
import eatyourbeets.cards.base.*;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.cards.base.modifiers.CostModifiers;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.effects.VFX;
import eatyourbeets.ui.common.EYBCardPopupActions;
import eatyourbeets.utilities.*;

public class Totori extends AnimatorCard {
    public static final EYBCardData DATA = Register(Totori.class)
            .SetAttack(2, CardRarity.RARE, EYBAttackType.Ranged, EYBCardTarget.ALL)
            .SetSeriesFromClassPackage()
            .PostInitialize(data ->
            {
                data.AddPopupAction(new EYBCardPopupActions.Atelier_Totori(Rorona.DATA, 50, Chim.DATA));
                data.AddPreview(new Chim(), true);
            });

    public Totori() {
        super(DATA);

        Initialize(3, 0, 2, 1);
        SetUpgrade(0, 0, 0, 1);

        SetAffinity_Blue(2, 0, 1);
        SetAffinity_Pink(1);

        SetExhaust(true);
        SetDelayed(true);
    }


    @Override
    public AbstractAttribute GetDamageInfo()
    {
        return super.GetDamageInfo().AddMultiplier(magicNumber);
    }

    @Override
    protected void OnUpgrade() {
        super.OnUpgrade();

        SetExhaust(false);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.Callback(() ->
        {
            for (AbstractCreature m1 : GameUtilities.GetEnemies(true))
            {
                GameEffects.List.Attack(player, m1, AttackEffects.LIGHTNING, 0.8f, 0.9f, Color.RED);
                GameEffects.List.Add(VFX.FlameBarrier(m1.hb));
                for (int i = 0; i < 4; i++)
                {
                    GameEffects.List.Add(VFX.SmallExplosion(m1.hb, 0.5f).PlaySFX(i == 0));
                }
            }
        });
        GameActions.Bottom.WaitRealtime(0.35f);
        for (int i=0; i<magicNumber; i++) {
            GameActions.Bottom.DealDamageToAll(this, AttackEffects.NONE);
        }

        RandomizedList<AbstractCard> possible = GetPossibleTargetsForCopy();

        if (possible.Size() > 0)
        {
            GameActions.Bottom.Callback(() -> CopyRandomCardOntoDrawPile(possible));
            GameActions.Bottom.Reload(name, cards ->
            {
                for (int i = 0; i < cards.size(); i++)
                {
                    CopyRandomCardOntoDrawPile(possible);
                }
            });
        }
    }

    private RandomizedList<AbstractCard> GetPossibleTargetsForCopy() {
        RandomizedList<AbstractCard> possibleTargets = new RandomizedList<>();

        for (AbstractCard card : player.masterDeck.group) {
            if (!card.exhaust && !card.exhaustOnUseOnce && !card.purgeOnUse && !card.hasTag(EYBCard.PURGE)) {
                possibleTargets.Add(card);
            }
        }

        return possibleTargets;
    }

    private void CopyRandomCardOntoDrawPile(RandomizedList<AbstractCard> possibleCards) {

        AbstractCard copyTarget = possibleCards.Retrieve(rng, false);

        GameActions.Top.MakeCardInDrawPile(copyTarget.makeCopy())
                .SetUpgrade(copyTarget.upgraded, true)
                .SetDestination(CardSelection.Top)
                .AddCallback(card -> {
                    CostModifiers.For(card).Add(-secondaryValue);
                });
    }
}