package eatyourbeets.cards.animator.series.GATE;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.TargetHelper;

import java.util.LinkedList;
import java.util.List;

public class RoryMercury extends AnimatorCard
{
    public static final EYBCardData DATA = Register(RoryMercury.class)
            .SetAttack(1, CardRarity.UNCOMMON, EYBAttackType.Normal, EYBCardTarget.Random)
            .SetSeriesFromClassPackage();

    private List<AbstractCreature> enemiesHit = new LinkedList<>();

    public RoryMercury()
    {
        super(DATA);

        Initialize(4, 0, 2);
        SetUpgrade(0, 0, 1);

        SetAffinity_Red(1, 0, 1);
        SetAffinity_Black(1);

        SetCardPreview(card -> card.type == CardType.ATTACK);
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

        for (int i=0; i<magicNumber; i++) {
            GameActions.Bottom.DealDamageToRandomEnemy(this, AttackEffects.SLASH_HEAVY).AddCallback(this::OnDamageDealt);
        }

        GameActions.Bottom.Callback(c -> {
           if (enemiesHit.size() == 1) {
               AbstractCreature enemy = enemiesHit.get(0);
               GameActions.Top.ApplyVulnerable(TargetHelper.Normal(enemy), 1);
           }
        });

        GameActions.Last.Callback(c ->{
            enemiesHit.clear();
        });
    }

    protected void OnDamageDealt(AbstractCreature target)
    {
        if (!enemiesHit.contains(target)) {
            enemiesHit.add(target);
            GameActions.Top.Draw(1)
             .SetFilter(card -> card.type == CardType.ATTACK, false);
        }
    }
}