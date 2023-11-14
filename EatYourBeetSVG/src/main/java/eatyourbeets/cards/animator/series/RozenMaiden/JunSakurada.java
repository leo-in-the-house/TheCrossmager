package eatyourbeets.cards.animator.series.RozenMaiden;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.*;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

import java.util.LinkedList;
import java.util.List;

public class JunSakurada extends AnimatorCard {
    public static final EYBCardData DATA = Register(JunSakurada.class)
            .SetSkill(1, CardRarity.UNCOMMON, EYBCardTarget.None)
            .SetSeriesFromClassPackage();

    private static final List<String> ROZEN_MAIDEN_DOLLS = new LinkedList<String>();

    public JunSakurada() {
        super(DATA);

        Initialize(0, 6, 0);
        SetUpgrade(0, 2, 0);

        SetAffinity_Pink(1, 0, 1);
        SetAffinity_White(1, 0, 1);

        ROZEN_MAIDEN_DOLLS.add(Hinaichigo.DATA.ID);
        ROZEN_MAIDEN_DOLLS.add(Souseiseki.DATA.ID);
        ROZEN_MAIDEN_DOLLS.add(Suiseiseki.DATA.ID);

        SetAffinityRequirement(Affinity.Pink, 2);
        SetAffinityRequirement(Affinity.White, 2);
    }

    @Override
    protected void OnUpgrade()
    {
        super.OnUpgrade();

        AddScaling(Affinity.Pink, 1);
        AddScaling(Affinity.White, 1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.GainBlock(block);

        boolean rightmostOnly = true;

        if (CheckSpecialCondition(false)) {
            rightmostOnly = false;
        }

        for (int i=0; i<player.hand.size(); i++) {
            AbstractCard card = player.hand.getNCardFromTop(i);
            if (card != null && isRozenMaidenDoll(card)) {
                GameUtilities.Retain(card);

                if (rightmostOnly) {
                    break;
                }
            }
        }
    }

    private boolean isRozenMaidenDoll(AbstractCard card) {
        return ROZEN_MAIDEN_DOLLS.contains(card.cardID);
    }
}