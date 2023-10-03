package eatyourbeets.cards.animator.series.GenshinImpact;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.colorless.uncommon.KaeyaAlberich;
import eatyourbeets.cards.animator.colorless.uncommon.LisaMinci;
import eatyourbeets.cards.animator.special.BarbaraPegg;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class JeanGunnhildr extends AnimatorCard
{
    public static final EYBCardData DATA = Register(JeanGunnhildr.class)
            .SetAttack(1, CardRarity.UNCOMMON)
            .SetSeriesFromClassPackage();
    static
    {
        DATA.AddPreview(new Amber(), true);
        DATA.AddPreview(new KaeyaAlberich(), true);
        DATA.AddPreview(new LisaMinci(), true);
        DATA.AddPreview(new BarbaraPegg(), true);
    }

    public JeanGunnhildr()
    {
        super(DATA);

        Initialize(10, 0);
        SetUpgrade(2, 0);

        SetAffinity_Brown(1);
        SetAffinity_Pink(1);

        SetExhaust(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.DealDamage(this, m, AttackEffects.SLASH_DIAGONAL);

        CardGroup group = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        group.addToBottom(new Amber());
        group.addToBottom(new KaeyaAlberich());
        group.addToBottom(new LisaMinci());
        group.addToBottom(new BarbaraPegg());

        GameActions.Bottom.SelectFromPile(name, 1, group)
            .SetOptions(false, false)
            .AddCallback(cards -> {
                for (AbstractCard card : cards) {
                    if (upgraded) {
                        card.upgrade();
                    }

                    GameActions.Top.MakeCardInHand(card);
                }
            });
    }
}