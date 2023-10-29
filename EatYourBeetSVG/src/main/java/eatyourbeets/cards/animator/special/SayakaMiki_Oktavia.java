package eatyourbeets.cards.animator.special;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.series.MadokaMagica.SayakaMiki;
import eatyourbeets.cards.base.*;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.effects.VFX;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class SayakaMiki_Oktavia extends AnimatorCard
{
    public static final EYBCardData DATA = Register(SayakaMiki_Oktavia.class)
            .SetAttack(3, CardRarity.SPECIAL, EYBAttackType.Elemental, EYBCardTarget.ALL)
            .SetSeries(SayakaMiki.DATA.Series);

    public SayakaMiki_Oktavia()
    {
        super(DATA);

        Initialize(25, 0, 5);
        SetUpgrade(12, 0, 0);

        SetAffinity_Pink(2, 0, 1);
        SetAffinity_Black(1, 0, 1);
    }

    @Override
    public AbstractAttribute GetDamageInfo()
    {
        if (GameUtilities.InBattle()) {
            int numCursesInExhaust = player.exhaustPile.getCardsOfType(CardType.CURSE).size();

            return super.GetDamageInfo().AddMultiplier(Math.min(magicNumber, numCursesInExhaust));
        } else {
            return super.GetDamageInfo().AddMultiplier(1);
        }
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.VFX(VFX.Mindblast(p.dialogX, p.dialogY).SetColor(Color.BLUE));

        GameActions.Bottom.PurgeFromPile(name, magicNumber, player.exhaustPile)
            .SetFilter(card -> card.type == CardType.CURSE)
            .SetOptions(true, true)
            .AddCallback(cards -> {
                for (AbstractCard card : cards) {
                    GameActions.Bottom.DealDamageToAll(this, AttackEffects.DARK);
                }
            });
    }
}
