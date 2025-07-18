package eatyourbeets.cards.animator.special;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.BorderLongFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.ShockWaveEffect;
import eatyourbeets.cards.base.*;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.interfaces.delegates.ActionT3;
import eatyourbeets.resources.GR;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class ByakuyaBankai extends AnimatorCard {
    public static final EYBCardData DATA = Register(ByakuyaBankai.class)
            .SetAttack(-1, CardRarity.SPECIAL, EYBAttackType.Ranged, EYBCardTarget.ALL)
            .SetSeries(CardSeries.Bleach);

    public ByakuyaBankai() {
        super(DATA);

        Initialize(26, 22);
        SetUpgrade(4, 4);

        SetAffinity_Red(1);
        SetAffinity_White(1);
        SetAffinity_Violet(1);

        SetMultiDamage(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        int stacks = GameUtilities.UseXCostEnergy(this);

        for (int i = 0; i < stacks; i++) {
            GameActions.Bottom.Callback(m, (enemy, __) ->
                    ChooseAction(enemy)
            );
        }
    }

    private void ChooseAction(AbstractMonster m) {
        AnimatorCard damage = GenerateInternal(CardType.ATTACK, this::DamageEffect).Build();
        AnimatorCard block = GenerateInternal(CardType.SKILL, this::BlockEffect).Build();

        CardGroup choices = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        choices.addToTop(damage);
        choices.addToTop(block);

        Execute(choices, m);
    }

    private AnimatorCardBuilder GenerateInternal(AbstractCard.CardType type, ActionT3<EYBCard, AbstractPlayer, AbstractMonster> onUseAction) {
        AnimatorCardBuilder builder = new AnimatorCardBuilder(ByakuyaBankai.DATA.ID);
        builder.SetText(name, "", "");
        builder.SetImagePath(ByakuyaBankai.DATA.ImagePath);
        builder.SetProperties(type, GR.Enums.Cards.THE_ANIMATOR, AbstractCard.CardRarity.RARE, CardTarget.ENEMY);
        builder.SetOnUse(onUseAction);

        if (type.equals(CardType.ATTACK)) {
            builder.SetAttackType(EYBAttackType.Ranged, EYBCardTarget.ALL);
            builder.SetNumbers(damage, 0, 0, 0);
        } else {
            builder.SetNumbers(0, block, 0, 0);
        }

        return builder;
    }

    private void Execute(CardGroup group, AbstractMonster m) {
        GameActions.Top.SelectFromPile(name, 1, group)
                .SetOptions(false, false)
                .AddCallback(cards ->
                {
                    AbstractCard card = cards.get(0);
                    card.applyPowers();
                    card.calculateCardDamage(m);
                    card.use(player, m);
                });
    }

    private void DamageEffect(AbstractCard card, AbstractPlayer p, AbstractMonster m) {
        GameActions.Bottom.VFX(new BorderLongFlashEffect(Color.WHITE));
        GameActions.Bottom.VFX(new ShockWaveEffect(p.hb.cX, p.hb.cY, Color.WHITE, ShockWaveEffect.ShockWaveType.ADDITIVE), 0.75f);

        GameActions.Bottom.DealDamageToAll(this, AttackEffects.SLASH_HEAVY);
    }

    private void BlockEffect(AbstractCard card, AbstractPlayer p, AbstractMonster m) {
        GameActions.Bottom.GainBlock(block);
    }
}