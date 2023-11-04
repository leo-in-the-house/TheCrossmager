package eatyourbeets.cards.animator.series.Overlord;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.monsters.EnemyIntent;
import eatyourbeets.powers.animator.EnchantedArmorPlayerPower;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.TargetHelper;

public class Albedo extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Albedo.class)
            .SetAttack(2, CardRarity.RARE)
            .SetSeriesFromClassPackage();

    public Albedo()
    {
        super(DATA);

        Initialize(2, 0, 3);
        SetUpgrade(0, 0, 3);

        SetAffinity_Pink(1, 0, 1);
        SetAffinity_Violet(1, 0, 1);

        SetEthereal(true);
    }

    @Override
    protected void OnUpgrade()
    {
        SetEthereal(false);
    }

    @Override
    public void OnDrag(AbstractMonster m)
    {
        super.OnDrag(m);

        int amountEnchantedArmorToGain = CalculateEnchantedArmorGain();

        if (amountEnchantedArmorToGain > 0) {
            for (EnemyIntent intent : GameUtilities.GetIntents())
            {
                intent.AddPlayerEnchantedArmor(amountEnchantedArmorToGain);
            }
        }
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.DealDamage(this, m, AttackEffects.SLASH_HEAVY);

        int amountEnchantedArmorToGain = CalculateEnchantedArmorGain();

        if (amountEnchantedArmorToGain > 0) {
            GameActions.Bottom.StackPower(new EnchantedArmorPlayerPower(p, amountEnchantedArmorToGain));
        }
    }

    private int CalculateEnchantedArmorGain() {
        return GameUtilities.GetCommonDebuffs(TargetHelper.Enemies()).size();
    }
}