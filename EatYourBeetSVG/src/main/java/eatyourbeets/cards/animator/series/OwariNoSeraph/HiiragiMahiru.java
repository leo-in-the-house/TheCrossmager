package eatyourbeets.cards.animator.series.OwariNoSeraph;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.special.HiiragiMahiru_Demon;
import eatyourbeets.cards.animator.special.HiiragiMahiru_Deva;
import eatyourbeets.cards.animator.special.HiiragiMahiru_Echo;
import eatyourbeets.cards.animator.special.HiiragiMahiru_Wraith;
import eatyourbeets.cards.base.*;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.cards.base.attributes.TempHPAttribute;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.effects.VFX;
import eatyourbeets.resources.GR;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameEffects;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.RandomizedList;

public class HiiragiMahiru extends AnimatorCard {
    public static final EYBCardData DATA = Register(HiiragiMahiru.class)
            .SetAttack(3, CardRarity.RARE, EYBAttackType.Normal, EYBCardTarget.ALL)
            .SetSeriesFromClassPackage()
            .PostInitialize(data ->
            {
                data.AddPreview(new HiiragiMahiru_Demon(), false);
                data.AddPreview(new HiiragiMahiru_Deva(), false);
                data.AddPreview(new HiiragiMahiru_Echo(), false);
                data.AddPreview(new HiiragiMahiru_Wraith(), false);
            });

    public HiiragiMahiru() {
        super(DATA);

        Initialize(10, 0, 6);
        SetCostUpgrade(-1);

        SetAffinity_White(1, 0, 1);
        SetAffinity_Black(1, 0, 1);
        SetAffinity_Violet(1, 0, 1);

        SetExhaust(true);
    }

    @Override
    public boolean cardPlayable(AbstractMonster m)
    {
        if (super.cardPlayable(m))
        {
            return player.exhaustPile.size() >= 10;
        }

        return false;
    }

    @Override
    public AbstractAttribute GetSpecialInfo()
    {
        return TempHPAttribute.Instance.SetCard(this, true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.DealDamageToAll(this, AttackEffects.SLASH_HEAVY)
            .SetDamageEffect((e, __) ->
            {
                GameEffects.List.BorderFlash(Color.RED);
                GameEffects.List.Add(VFX.Hemokinesis(player.hb, e.hb));
            });

        GameActions.Bottom.GainTemporaryHP(magicNumber);

        CardGroup choices = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);

        RandomizedList<AbstractCard> possibleChoices = new RandomizedList<AbstractCard>();

        possibleChoices.Add(new HiiragiMahiru_Demon());
        possibleChoices.Add(new HiiragiMahiru_Deva());
        possibleChoices.Add(new HiiragiMahiru_Echo());
        possibleChoices.Add(new HiiragiMahiru_Wraith());

        for (int i=0; i<2; i++) {
            choices.addToBottom(possibleChoices.Retrieve(rng, true));
        }

        GameActions.Bottom.SelectFromPile(name, 1, choices)
            .SetMessage(GR.Common.Strings.GridSelection.ChooseAndPlay)
            .AddCallback(cards -> {
               for (AbstractCard card : cards) {
                    GameActions.Top.PlayCard(card, m);
               }
            });
    }
}