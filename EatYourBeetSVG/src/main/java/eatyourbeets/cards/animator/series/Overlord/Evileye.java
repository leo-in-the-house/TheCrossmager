package eatyourbeets.cards.animator.series.Overlord;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.BorderLongFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.ShockWaveEffect;
import eatyourbeets.cards.base.*;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.TargetHelper;

public class Evileye extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Evileye.class)
            .SetSkill(2, CardRarity.UNCOMMON, EYBCardTarget.None)
            .SetSeriesFromClassPackage();

    public Evileye()
    {
        super(DATA);

        Initialize(0,0,2);
        SetUpgrade(0, 0, 2);

        SetAffinity_Violet(1);
        SetAffinity_Blue(1);
        SetAffinity_Black(1);

        SetExhaust(true);
        SetRetain(true);

        SetAffinityRequirement(Affinity.Violet, 3);
        SetAffinityRequirement(Affinity.Blue, 3);
        SetAffinityRequirement(Affinity.Black, 3);
    }

    @Override
    protected void OnUpgrade()
    {
        SetExhaust(false);
    }
    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.VFX(new BorderLongFlashEffect(Color.VIOLET));
        ActivateEvileye(p);

        GameActions.Bottom.Reload(name, cards ->
        {
            for (int i = 0; i < cards.size(); i++)
            {
                ActivateEvileye(p);
            }
        });
    }

    private void ActivateEvileye(AbstractPlayer p) {
        GameActions.Bottom.VFX(new ShockWaveEffect(p.hb.cX, p.hb.cY, Color.VIOLET, ShockWaveEffect.ShockWaveType.ADDITIVE), 0.75f);

        GameActions.Bottom.StackPower(TargetHelper.Enemies(), GameUtilities.GetRandomElement(GameUtilities.GetCommonDebuffs()), magicNumber)
                .ShowEffect(true, true);

        if (CheckSpecialCondition(false)) {
            AbstractCard card = p.discardPile.getTopCard();

            if (card != null) {
                GameActions.Bottom.PlayCard(card.makeCopy(), GameUtilities.GetRandomEnemy(true));
            }
        }
    }
}