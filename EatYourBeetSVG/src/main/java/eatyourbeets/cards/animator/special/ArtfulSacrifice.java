package eatyourbeets.cards.animator.special;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.series.TouhouProject.AliceMargatroid;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.cards.base.EYBCardTarget;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.effects.VFX;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class ArtfulSacrifice extends AnimatorCard {
    public static final EYBCardData DATA = Register(ArtfulSacrifice.class)
            .SetSkill(0, CardRarity.SPECIAL, EYBCardTarget.None)
            .SetSeries(AliceMargatroid.DATA.Series)
            .PostInitialize(data -> data.AddPreview(new ShanghaiDoll(), false));

    public ArtfulSacrifice() {
        super(DATA);

        Initialize(0, 0, 20);
        SetUpgrade(0, 0, 5);

        SetAffinity_Brown(1);
        SetAffinity_Red(1);

        SetPurge(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        for (AbstractCard card : player.hand.group) {
            if (card.cardID.equals(ShanghaiDoll.DATA.ID)) {
                GameActions.Bottom.Exhaust(card);
                GameActions.Bottom.DealDamageToRandomEnemy(magicNumber, DamageInfo.DamageType.THORNS, AttackEffects.FIRE)
                    .SetDamageEffect(e -> {
                        GameActions.Top.VFX(VFX.Fireball(player.hb, e.hb).SetColor(Color.RED, Color.YELLOW).SetScale(2).SetRealtime(true));
                        return 0f;
                    });
            }
        }
    }


}