package eatyourbeets.cards.animator.special;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.series.Konosuba.Hans;
import eatyourbeets.cards.animator.series.Konosuba.Sylvia;
import eatyourbeets.cards.animator.series.Konosuba.Verdia;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.resources.GR;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Sylvia_Chimera extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Sylvia_Chimera.class)
            .SetAttack(2, CardRarity.SPECIAL)
            .SetSeries(Sylvia.DATA.Series)
            .PostInitialize(data ->
            {
                data.AddPreview(new Sylvia(), true);
                data.AddPreview(new Hans(), true);
                data.AddPreview(new Verdia(), true);
            });
    public static final int SEAL_AMOUNT = 6;

    public Sylvia_Chimera()
    {
        super(DATA);

        Initialize(6, 12, 2);
        SetUpgrade(0, 0, 0);

        SetAffinity_Star(1, 0, 1);

        SetAutoplayed(true);
    }

    @Override
    public AbstractAttribute GetDamageInfo()
    {
        return super.GetDamageInfo().AddMultiplier(magicNumber);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.GainBlock(block);

        for (int i=0; i<magicNumber; i++) {
            GameActions.Bottom.DealDamage(this, m, AttackEffects.SLASH_HEAVY);
        }

        CardGroup group = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);

        Sylvia sylvia = new Sylvia();

        if (upgraded) {
            sylvia.upgrade();
        }

        Hans hans = new Hans();

        if (upgraded) {
            hans.upgrade();
        }

        Verdia beldia = new Verdia();

        if (upgraded) {
            beldia.upgrade();
        }

        group.addToBottom(sylvia);
        group.addToBottom(hans);
        group.addToBottom(beldia);

        GameActions.Bottom.SelectFromPile(name, 2, group)
           .SetMessage(GR.Common.Strings.HandSelection.Activate)
           .SetOptions(false, false, false)
            .AddCallback(cards -> {
                for (AbstractCard card : cards) {
                    GameActions.Top.PlayCopy(card, GameUtilities.GetRandomEnemy(true));
                }
            });

    }
}