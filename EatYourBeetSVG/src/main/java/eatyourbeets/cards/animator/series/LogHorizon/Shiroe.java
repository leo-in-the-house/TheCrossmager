package eatyourbeets.cards.animator.series.LogHorizon;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class Shiroe extends AnimatorCard
{
    public static final EYBCardData DATA = Register(Shiroe.class)
            .SetSkill(1, CardRarity.RARE, EYBCardTarget.None)
            .SetSeriesFromClassPackage();

    public Shiroe()
    {
        super(DATA);

        Initialize(0, 0);

        SetAffinity_White(1);
        SetAffinity_Pink(1);

        SetExhaust(true);
    }

    @Override
    protected void OnUpgrade()
    {
        super.OnUpgrade();

        SetInnate(true);
        SetHaste(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.Callback(() -> {
            UpdateAttacksAndSkills(player.drawPile);
            UpdateAttacksAndSkills(player.discardPile);
            UpdateAttacksAndSkills(player.hand);
            GameActions.Last.Callback(() -> {
               player.drawPile.shuffle(rng);
            });
        });
    }

    private void UpdateAttacksAndSkills(CardGroup group) {
        for (AbstractCard card : group.group) {
            if (card.type == CardType.ATTACK && card instanceof EYBCard) {
                ((EYBCard) card).SetDelayed(true);
            }
            else if (card.type == CardType.SKILL && card instanceof EYBCard) {
                ((EYBCard) card).SetRetain(true);
            }
        }
    }
}