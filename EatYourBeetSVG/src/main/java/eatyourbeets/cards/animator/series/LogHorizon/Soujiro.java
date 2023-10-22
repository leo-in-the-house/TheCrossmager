package eatyourbeets.cards.animator.series.LogHorizon;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.special.Soujiro_Isami;
import eatyourbeets.cards.animator.special.Soujiro_Kawara;
import eatyourbeets.cards.animator.special.Soujiro_Kurinon;
import eatyourbeets.cards.animator.special.Soujiro_Nazuna;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBAttackType;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

import java.util.ArrayList;

public class Soujiro extends AnimatorCard
{
    private static final ArrayList<AbstractCard> cardPool = new ArrayList<>();
    private static final CardGroup cardChoices = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);

    public static final EYBCardData DATA = Register(Soujiro.class)
            .SetAttack(3, CardRarity.RARE, EYBAttackType.Normal)
            .SetSeriesFromClassPackage()
            .PostInitialize(data ->
            {
                cardPool.add(new Soujiro_Isami());
                cardPool.add(new Soujiro_Kawara());
                cardPool.add(new Soujiro_Kurinon());
                cardPool.add(new Soujiro_Nazuna());
                for (AbstractCard c : cardPool)
                {
                    data.AddPreview(c, true);
                }
            });

    public Soujiro()
    {
        super(DATA);

        Initialize(8, 0, 2);
        SetUpgrade(0, 0, 0);

        SetAffinity_Green(2, 0, 2);
        SetAffinity_Red(1);

        SetDelayed(true);
        SetRetain(true);
    }

    @Override
    public AbstractAttribute GetDamageInfo()
    {
        return super.GetDamageInfo().AddMultiplier(magicNumber);
    }

    @Override
    public boolean cardPlayable(AbstractMonster m) {
        if (super.cardPlayable(m)) {
            return player.drawPile.size() == 0;
        }

        return false;
    }

    @Override
    public void triggerWhenCreated(boolean startOfBattle)
    {
        super.triggerWhenCreated(startOfBattle);

        if (startOfBattle) {
            for (AbstractCard card : cardPool) {
                GameActions.Bottom.MakeCardInDrawPile(card.makeCopy())
                .SetUpgrade(upgraded, true);
            }
        }
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);

        for (int i=0; i<magicNumber; i++) {
            GameActions.Bottom.DealDamageToAll(this, AttackEffects.SLASH_HEAVY);
        }
    }
}