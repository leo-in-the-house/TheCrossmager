package eatyourbeets.cards.animator.colorless.uncommon;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.MonsterGroup;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.vfx.combat.FallingIceEffect;
import com.megacrit.cardcrawl.vfx.combat.FireballEffect;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;
import com.megacrit.cardcrawl.vfx.combat.WhirlwindEffect;
import eatyourbeets.actions.damage.DealDamageToRandomEnemy;
import eatyourbeets.cards.base.*;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.interfaces.delegates.ActionT0;
import eatyourbeets.stances.IntellectStance;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameEffects;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.RandomizedList;

import java.util.HashSet;

public class Patchouli extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Patchouli.class)
            .SetAttack(3, CardRarity.UNCOMMON, EYBAttackType.Elemental, EYBCardTarget.Random)
            .SetColor(CardColor.COLORLESS)
            .SetSeries(CardSeries.TouhouProject);

    private final HashSet<String> uniqueOrbs = new HashSet<>();

    public Patchouli()
    {
        super(DATA);

        Initialize(15, 0, 1);
        SetUpgrade(12, 0, 0);

        SetAffinity_Blue(1, 0, 1);
        SetAffinity_Red(1, 0, 1);
        SetAffinity_Green(1, 0, 1);
    }

    @Override
    public AbstractAttribute GetDamageInfo()
    {
        return super.GetDamageInfo().AddMultiplier(magicNumber);
    }

    @Override
    public void Refresh(AbstractMonster enemy)
    {
        super.Refresh(enemy);

        uniqueOrbs.clear();

        for (AbstractOrb orb : AbstractDungeon.actionManager.orbsChanneledThisCombat)
        {
            uniqueOrbs.add(orb.ID);
        }

        GameUtilities.IncreaseMagicNumber(this, uniqueOrbs.size(), true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        final RandomizedList<ActionT0> actions = new RandomizedList<>();
        for (int i = 0; i < magicNumber; i++)
        {
            if (actions.Size() == 0)
            {
                actions.Add(this::Aether);
                actions.Add(this::Fire);
                actions.Add(this::Frost);
                actions.Add(this::Lightning);
            }

            actions.Retrieve(rng).Invoke();
            GameActions.Bottom.WaitRealtime(0.2f);
        }
    }

    @Override
    public boolean CheckSpecialCondition(boolean tryUse)
    {
        return super.CheckSpecialConditionLimited(tryUse, use -> IntellectStance.IsActive() || super.CheckSpecialCondition(use));
    }

    private void Lightning()
    {
        CreateDamageAction().SetDamageEffect(e ->
        {
            CardCrawlGame.sound.play("ORB_LIGHTNING_EVOKE", 0.2f);
            GameEffects.Queue.Add(new LightningEffect(e.drawX, e.drawY));
            return 0f;
        });
    }

    private void Frost()
    {
        CreateDamageAction().SetDamageEffect(e ->
        {
            MonsterGroup monsters = AbstractDungeon.getMonsters();
            int frostCount = monsters.monsters.size() + 5;

            CardCrawlGame.sound.playA("ORB_FROST_CHANNEL", -0.25f - (float) frostCount / 200f);
            for (int f = 0; f < frostCount; f++)
            {
                GameEffects.Queue.Add(new FallingIceEffect(frostCount, monsters.shouldFlipVfx()));
            }

            return 0f;
        });
    }

    private void Aether()
    {
        CreateDamageAction().SetDamageEffect(__ ->
        {
            CardCrawlGame.sound.play("ATTACK_WHIRLWIND", 0.2f);
            GameEffects.Queue.Add(new WhirlwindEffect());
            return 0f;
        });
    }

    private void Fire()
    {
        CreateDamageAction().SetDamageEffect(e ->
        {
            CardCrawlGame.sound.play("ATTACK_FIRE", 0.2f);
            GameEffects.Queue.Add(new FireballEffect(player.hb.cX, player.hb.cY, e.hb.cX, e.hb.cY));
            return 0f;
        });
    }

    private DealDamageToRandomEnemy CreateDamageAction()
    {
        return GameActions.Bottom.DealDamageToRandomEnemy(this, AttackEffects.NONE).SetOptions(true, false);
    }
}