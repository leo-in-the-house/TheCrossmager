package eatyourbeets.cards.animatorClassic.special;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.utility.ShakeScreenAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.MindblastEffect;
import eatyourbeets.actions.animator.CreateRandomCurses;
import eatyourbeets.cards.base.*;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.utilities.GameActions;

public class SayakaMiki_Oktavia extends AnimatorClassicCard
{
    public static final EYBCardData DATA = Register(SayakaMiki_Oktavia.class).SetAttack(2, CardRarity.SPECIAL, EYBAttackType.Elemental, EYBCardTarget.ALL);

    public SayakaMiki_Oktavia()
    {
        super(DATA);

        Initialize(9, 0, 1, 2);
        SetUpgrade(2, 0, 0, 0);
        SetScaling(1, 0, 0);

        this.series = CardSeries.MadokaMagica;
        
    }

    @Override
    protected void Refresh(AbstractMonster enemy)
    {
        super.Refresh(enemy);

        magicNumber = player.hand.getCardsOfType(CardType.CURSE).size();
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
        GameActions.Bottom.Add(new CreateRandomCurses(secondaryValue, p.hand));
        GameActions.Bottom.Callback(() ->
        { //
            GameActions.Bottom.Draw(player.hand.getCardsOfType(CardType.CURSE).size())
            .AddCallback(cards ->
            {
                GameActions.Bottom.VFX(new BorderFlashEffect(Color.BLACK));
                GameActions.Bottom.SFX("MONSTER_COLLECTOR_DEBUFF");
                GameActions.Bottom.VFX(new MindblastEffect(player.dialogX, player.dialogY, player.flipHorizontal), 0.2f);
                GameActions.Bottom.Add(new ShakeScreenAction(0.5f, ScreenShake.ShakeDur.MED, ScreenShake.ShakeIntensity.HIGH));

                for (int i = 0; i < player.hand.getCardsOfType(CardType.CURSE).size(); i++)
                {
                    GameActions.Bottom.DealDamageToAll(this, AttackEffects.NONE)
                    .SetVFX(false, true);
                }
            });
        });
    }
}
