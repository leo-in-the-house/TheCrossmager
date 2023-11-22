package eatyourbeets.cards.animator.series.Elsword;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.BlizzardEffect;
import eatyourbeets.cards.animator.tokens.AffinityToken;
import eatyourbeets.cards.base.*;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Ain extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Ain.class)
            .SetAttack(2, CardRarity.COMMON, EYBAttackType.Elemental, EYBCardTarget.ALL)
            .SetSeries(CardSeries.Elsword)
            .PostInitialize(data -> data.AddPreview(AffinityToken.GetCard(Affinity.Blue), true));

    public Ain()
    {
        super(DATA);

        Initialize(3, 0, 3);
        SetUpgrade(0, 0, 2);

        SetAffinity_Blue(2);

        SetRetain(true);
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
        GameActions.Bottom.VFX(new BlizzardEffect(magicNumber, AbstractDungeon.getMonsters().shouldFlipVfx()), 0.6f);

        for (int i = 0; i < magicNumber; i++)
        {
            GameActions.Bottom.DealDamageToAll(this, AttackEffects.NONE).SetVFX(true, false);
        }

        GameActions.Bottom.ObtainAffinityToken(Affinity.Blue, upgraded);
    }
}