package eatyourbeets.cards.animator.series.TouhouProject;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.MonsterRoomBoss;
import com.megacrit.cardcrawl.rooms.MonsterRoomElite;
import eatyourbeets.cards.base.*;
import eatyourbeets.cards.base.attributes.AbstractAttribute;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.effects.SFX;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.relics.animator.ShinigamiFerry;
import eatyourbeets.relics.animator.unnamedReign.AncientMedallion;
import eatyourbeets.relics.animator.unnamedReign.UnnamedReignRelic;
import eatyourbeets.utilities.GameActions;

public class KomachiOnozuka extends AnimatorCard
{
    public static final EYBCardData DATA = Register(KomachiOnozuka.class)
            .SetAttack(2, CardRarity.UNCOMMON, EYBAttackType.Piercing)
            .SetSeriesFromClassPackage();

    private static final AbstractRelic relicReward = new ShinigamiFerry();
    private static final EYBCardTooltip tooltip = new EYBCardTooltip(relicReward.name, relicReward.description);

    public KomachiOnozuka()
    {
        super(DATA);

        Initialize(5, 0, 2, 2);
        SetUpgrade(0, 0, 1, 2);

        SetAffinity_Black(1, 0, 1);
        SetAffinity_Blue(1, 0, 1);

        SetObtainableInCombat(false);
    }

    @Override
    public AbstractAttribute GetDamageInfo()
    {
        return super.GetDamageInfo().AddMultiplier(magicNumber);
    }

    @Override
    public void initializeDescription()
    {
        super.initializeDescription();

        if (cardText != null)
        {
            tooltips.add(tooltip);
        }
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);

        for (int i=0; i<magicNumber; i++) {
            GameActions.Bottom.DealDamage(this, m, AttackEffects.SLASH_HORIZONTAL)
                .SetDamageEffect(e -> {
                    GameActions.Bottom.SFX(SFX.ATTACK_REAPER, 0.95f, 1.05f);
                    return 0f;
                })
                .SetSoundPitch(0, 0)
                .AddCallback(enemy ->
                {
                    AbstractRoom room = AbstractDungeon.getCurrRoom();
                    if ((room instanceof MonsterRoomElite || room instanceof MonsterRoomBoss)
                            && GameUtilities.IsFatal(enemy, false)
                            && CombatStats.TryActivateLimited(cardID))
                    {
                        ObtainReward();
                    }
                });
        }

        GameActions.Bottom.ApplyVulnerable(p, m, secondaryValue);
    }

    public void ObtainReward()
    {
        if (UnnamedReignRelic.IsEquipped())
        {
            GameUtilities.GetCurrentRoom(true).addRelicToRewards(new AncientMedallion());
        }
        else
        {
            GameUtilities.GetCurrentRoom(true).addRelicToRewards(relicReward.makeCopy());
        }
    }
}

