package patches.merchant;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.shop.Merchant;
import eatyourbeets.cards.base.EYBCard;
import eatyourbeets.interfaces.listeners.OnAddingToCardRewardListener;
import eatyourbeets.utilities.JUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class MerchantPatches
{
    @SpirePatch(clz = Merchant.class, method = SpirePatch.CONSTRUCTOR, paramtypez = {float.class, float.class, int.class})
    public static class MerchantPatches_initCards
    {
        private static final HashMap<Merchant, MerchantData> map = new HashMap<>();

        public static class MerchantData
        {
            public CardGroup colorless;
            public CardGroup common;
            public CardGroup uncommon;
            public CardGroup rare;

            protected CardGroup GetReplacement(CardGroup group)
            {
                final CardGroup replacement = new CardGroup(CardGroup.CardGroupType.CARD_POOL);
                for (AbstractCard c : group.group)
                {
                    final EYBCard card = JUtils.SafeCast(c, EYBCard.class);
                    if (card == null) {
                        //No non-animator cards!
                        continue;
                    }

                    if (card != null && card.cardData.ShouldCancel())
                    {
                        continue;
                    }
                    if (c instanceof OnAddingToCardRewardListener && ((OnAddingToCardRewardListener)c).ShouldCancel())
                    {
                        continue;
                    }

                    replacement.group.add(c);
                }

                return replacement;
            }
        }

        @SpirePrefixPatch
        public static void Prefix(Merchant __instance, float x, float y, int newShopScreen)
        {
            final MerchantData data = new MerchantData();
            map.put(__instance, data);

            data.colorless = AbstractDungeon.colorlessCardPool;
            AbstractDungeon.colorlessCardPool = data.GetReplacement(data.colorless);

            data.common = AbstractDungeon.commonCardPool;
            AbstractDungeon.commonCardPool = data.GetReplacement(data.common);

            data.uncommon = AbstractDungeon.uncommonCardPool;
            AbstractDungeon.uncommonCardPool = data.GetReplacement(data.uncommon);

            data.rare = AbstractDungeon.rareCardPool;
            AbstractDungeon.rareCardPool = data.GetReplacement(data.rare);
        }

        @SpirePostfixPatch
        public static void Postfix(Merchant __instance, float x, float y, int newShopScreen, ArrayList<AbstractCard> ___cards2)
        {
            final MerchantData data = map.remove(__instance);

            data.colorless = AbstractDungeon.colorlessCardPool;
            AbstractDungeon.colorlessCardPool = data.GetReplacement(data.colorless);

            AbstractDungeon.commonCardPool = data.common;
            AbstractDungeon.uncommonCardPool = data.uncommon;
            AbstractDungeon.rareCardPool = data.rare;
            //Prevent this from being overriden with even mods
            float drawStartX = (float)Settings.WIDTH * 0.16F;
            int tmp = (int)((float)Settings.WIDTH - drawStartX * 2.0F - AbstractCard.IMG_WIDTH_S * 5.0F) / 4;
            float padX = (float)((int)((float)tmp + AbstractCard.IMG_WIDTH_S));

            AbstractDungeon.shopScreen.colorlessCards.clear();

            ___cards2.clear();

            for (int i=0; i<2; i++)
            {
                AbstractCard card = AbstractDungeon.getColorlessCardFromPool(i == 0 ? AbstractCard.CardRarity.UNCOMMON : AbstractCard.CardRarity.RARE).makeCopy();
                card.price = i == 0 ? 30 : 60;
                card.updateHoverLogic();
                card.targetDrawScale = 0.75F;
                card.current_x = drawStartX + AbstractCard.IMG_WIDTH_S / 2.0F + padX * (float)i;
                card.target_x = drawStartX + AbstractCard.IMG_WIDTH_S / 2.0F + padX * (float)i;
                card.target_y = 9999.0F * Settings.scale;
                card.current_y = 9999.0F * Settings.scale;

                Iterator var6 = AbstractDungeon.player.relics.iterator();

                while(var6.hasNext()) {
                    AbstractRelic r = (AbstractRelic)var6.next();
                    r.onPreviewObtainCard(card);
                }
                ___cards2.add(card);
            }
        }
    }
}
