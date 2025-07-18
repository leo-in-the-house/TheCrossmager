package eatyourbeets.cards.animator.series.Bleach;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import eatyourbeets.utilities.GameUtilities;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.stances.NeutralStance;
import eatyourbeets.cards.animator.special.ByakuyaBankai;
import eatyourbeets.cards.base.*;
import eatyourbeets.interfaces.delegates.ActionT3;
import eatyourbeets.resources.GR;
import eatyourbeets.stances.WrathStance;
import eatyourbeets.utilities.GameActions;

public class ByakuyaKuchiki extends AnimatorCard
{
    public static final EYBCardData DATA = Register(ByakuyaKuchiki.class).SetAttack(2, CardRarity.RARE, EYBAttackType.Piercing)
            .SetSeriesFromClassPackage();
    static
    {
        DATA.AddPreview(new ByakuyaBankai(), true);
    }

    public ByakuyaKuchiki()
    {
        super(DATA);

        Initialize(26, 22, 0);
        SetUpgrade(4, 4, 0);
        
        SetAffinity_White(1);
        SetAffinity_Violet(1);
        
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);
        GameActions.Bottom.Callback(card -> {
            ChooseAction(m);
        });

        if (WrathStance.IsActive())
        {
            GameActions.Bottom.ChangeStance(NeutralStance.STANCE_ID);
            GameActions.Bottom.MakeCardInDrawPile(new ByakuyaBankai())
                    .SetUpgrade(upgraded, true);
            GameActions.Last.ModifyAllInstances(uuid).AddCallback(GameActions.Bottom::Exhaust);
        }
    }

    private void ChooseAction(AbstractMonster m)
    {
        AnimatorCard damage = GenerateInternal(CardType.ATTACK, this::DamageEffect).Build();
        AnimatorCard block = GenerateInternal(CardType.SKILL, this::BlockEffect).Build();

        CardGroup choices = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        choices.addToTop(damage);
        choices.addToTop(block);

        Execute(choices, m);
    }

    private AnimatorCardBuilder GenerateInternal(AbstractCard.CardType type, ActionT3<EYBCard, AbstractPlayer, AbstractMonster> onUseAction)
    {
        AnimatorCardBuilder builder = new AnimatorCardBuilder(ByakuyaKuchiki.DATA.ID);
        builder.SetImagePath(ByakuyaKuchiki.DATA.ImagePath);
        builder.SetText(name, "", "");
        builder.SetProperties(type, GR.Enums.Cards.THE_ANIMATOR, AbstractCard.CardRarity.RARE, CardTarget.ENEMY);
        builder.SetOnUse(onUseAction);

        if (type.equals(CardType.ATTACK))
        {
            builder.SetAttackType(EYBAttackType.Piercing, EYBCardTarget.Normal);
            builder.SetNumbers(damage, 0, 0, 0);
        }
        else
        {
            builder.SetNumbers(0, block, 0, 0);
        }

        return builder;
    }

    private void Execute(CardGroup group, AbstractMonster m)
    {
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

    private void DamageEffect(AbstractCard card, AbstractPlayer p, AbstractMonster m)
    {
        GameActions.Bottom.DealDamage(this, m, AbstractGameAction.AttackEffect.SMASH);
    }

    private void BlockEffect(AbstractCard card, AbstractPlayer p, AbstractMonster m)
    {
        GameActions.Bottom.GainBlock(block);
    }
}