package eatyourbeets.cards.animator.series.RozenMaiden;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.ultrarare.Kirakishou;
import eatyourbeets.cards.base.*;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

import java.util.LinkedList;
import java.util.List;

public class JunSakurada extends AnimatorCard {
    public static final EYBCardData DATA = Register(JunSakurada.class)
            .SetSkill(1, CardRarity.UNCOMMON, EYBCardTarget.Normal)
            .SetSeriesFromClassPackage()
            .PostInitialize(data ->
            {
                data.AddPreview(new Suigintou(), true);
                data.AddPreview(new Kanaria(), true);
                data.AddPreview(new Souseiseki(), true);
                data.AddPreview(new Suiseiseki(), true);
                data.AddPreview(new Shinku(), true);
                data.AddPreview(new Hinaichigo(), true);
            });

    private static final List<String> ROZEN_MAIDEN_DOLLS = new LinkedList<String>();

    public JunSakurada() {
        super(DATA);

        Initialize(0, 8, 0);
        SetUpgrade(0, 2, 0);

        SetAffinity_Pink(1, 1, 0);
        SetAffinity_White(1, 1, 0);

        ROZEN_MAIDEN_DOLLS.add(Suigintou.DATA.ID);
        ROZEN_MAIDEN_DOLLS.add(Kanaria.DATA.ID);
        ROZEN_MAIDEN_DOLLS.add(Souseiseki.DATA.ID);
        ROZEN_MAIDEN_DOLLS.add(Suiseiseki.DATA.ID);
        ROZEN_MAIDEN_DOLLS.add(Shinku.DATA.ID);
        ROZEN_MAIDEN_DOLLS.add(Hinaichigo.DATA.ID);
        ROZEN_MAIDEN_DOLLS.add(Kirakishou.DATA.ID);

        SetAffinityRequirement(Affinity.Pink, 2);
        SetAffinityRequirement(Affinity.White, 2);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info) {
        GameUtilities.PlayVoiceSFX(name);

        GameActions.Bottom.GainBlock(block);


        if (CheckSpecialCondition(false)) {
            for (int i=0; i<player.hand.size(); i++) {
                AbstractCard card = player.hand.getNCardFromTop(i);
                if (card != null && isRozenMaidenDoll(card)) {
                    GameActions.Bottom.PlayCopy(card, m);
                    GameActions.Bottom.PlayCopy(card, m);
                    GameActions.Bottom.PlayCard(card, m);
                    break;
                }
            }
        }

    }

    private boolean isRozenMaidenDoll(AbstractCard card) {
        return ROZEN_MAIDEN_DOLLS.contains(card.cardID);
    }
}