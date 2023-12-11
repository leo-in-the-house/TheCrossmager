package eatyourbeets.cards.animator.special;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import eatyourbeets.cards.base.*;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.cards.base.attributes.TempHPAttribute;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.effects.SFX;
import eatyourbeets.effects.VFX;
import eatyourbeets.interfaces.listeners.OnAddToDeckListener;
import eatyourbeets.utilities.ColoredString;
import eatyourbeets.utilities.GameActions;

public class DeepPrincess extends AnimatorCard implements OnAddToDeckListener
{
    public static final int COST = 12;
    public static final EYBCardData DATA = Register(DeepPrincess.class)
            .SetAttack(-1, CardRarity.SPECIAL, EYBAttackType.Elemental, EYBCardTarget.ALL)
            .SetColor(CardColor.COLORLESS)
            .SetSeries(CardSeries.EtrianOdyssey)
            .PostInitialize(data -> data.AddPreview(new DeepPrincess_Madness(), false));

    public DeepPrincess()
    {
        super(DATA);

        Initialize(600, 0, 200);
        SetUpgrade(300, 0, 300);

        SetAffinity_Star(2, 0, 1);
    }

    @Override
    public boolean OnAddToDeck()
    {
        SFX.Play(SFX.NECRONOMICON, 0.666f, 0.666f);

        return true;
    }

    @Override
    public AbstractAttribute GetSpecialInfo()
    {
        return TempHPAttribute.Instance.SetCard(this, true);
    }

    @Override
    protected ColoredString GetCostString()
    {
        ColoredString res = super.GetCostString();
        res.text = String.valueOf(COST);
        return res;
    }

    @Override
    public boolean cardPlayable(AbstractMonster m)
    {
        return super.cardPlayable(m) && player.hand.contains(this);
    }

    @Override
    public boolean hasEnoughEnergy()
    {
        this.costForTurn = COST;
        this.freeToPlayOnce = false;
        boolean res = super.hasEnoughEnergy();
        this.costForTurn = -2;
        return res;
    }

    @Override
    public boolean freeToPlay()
    {
        return false;
    }

    @Override
    public boolean canUpgrade()
    {
        return false;
    }

    @Override
    public void triggerWhenDrawn()
    {
        super.triggerWhenDrawn();

        switch (MathUtils.random(2))
        {
            case 0: SFX.Play(SFX.VO_AWAKENEDONE_1, 0.5f, 0.75f); break;
            case 1: SFX.Play(SFX.VO_AWAKENEDONE_2, 0.5f, 0.75f); break;
            case 2: SFX.Play(SFX.VO_AWAKENEDONE_3, 0.5f, 0.75f); break;
        }

        GameActions.Bottom.MakeCardInHand(new DeepPrincess_Madness());
        GameActions.Bottom.Flash(this);
    }

//    @Override
//    protected void Refresh(AbstractMonster enemy)
//    {
//        super.Refresh(enemy);
//
//        GameUtilities.ModifyCostForCombat(this, COST, false);
//    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        if (EnergyPanel.getCurrentEnergy() < COST)
        {
            return;
        }

        EnergyPanel.useEnergy(COST);
        GameActions.Bottom.GainTemporaryHP(magicNumber);
        GameActions.Bottom.VFX(VFX.Cataclysm(), 0.8f, true)
        .AddCallback(__ -> GameActions.Top.DealDamageToAll(this, AttackEffects.NONE));
    }
}