package eatyourbeets.cards.animator.series.OwariNoSeraph;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.special.MiraiKimizuki;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.ui.common.EYBCardPopupActions;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class KimizugiShiho extends AnimatorCard
{
    public static final EYBCardData DATA = Register(KimizugiShiho.class)
            .SetAttack(1, CardRarity.UNCOMMON)
            .SetSeriesFromClassPackage()
            .PostInitialize(data ->
            {
                data.AddPopupAction(new EYBCardPopupActions.OwariNoSeraph_Mirai(MiraiKimizuki.DATA, HiiragiKureto.DATA, 6));
                data.AddPreview(new MiraiKimizuki(), false);
            });

    public KimizugiShiho()
    {
        super(DATA);

        Initialize(4, 0, 2);
        SetUpgrade(0, 0, 2);

        SetAffinity_Red(1, 0, 1);
        SetAffinity_Teal(1, 0, 1);

        SetDelayed(true);
    }

    @Override
    public AbstractAttribute GetDamageInfo()
    {
        return super.GetDamageInfo().AddMultiplier(magicNumber);
    }


    @Override
    public void triggerOnExhaust()
    {
        super.triggerOnExhaust();

        GameActions.Bottom.SelectFromPile(name, 1, player.exhaustPile)
            .SetOptions(false, true,  true)
            .SetFilter(c -> c.rarity == CardRarity.COMMON || c.rarity == CardRarity.SPECIAL)
            .SetMessage(cardData.Strings.EXTENDED_DESCRIPTION[0])
            .AddCallback(cards ->
            {
                if (cards.size() > 0 )
                {
                    AbstractCard card = cards.get(0);

                    GameActions.Top.MoveCard(card, player.discardPile);
                }
            });
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        for (int i = 0; i < magicNumber; i++)
        {
            GameActions.Bottom.DealDamage(this, m, AttackEffects.DAGGER).SetVFXColor(Color.RED);
        }
    }
}