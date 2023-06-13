package team.teampotato.EternalPotionEffects.Event;

import net.fabricmc.api.ModInitializer;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Items;
import org.apache.commons.lang3.tuple.Pair;

import static team.teampotato.EternalPotionEffects.Event.InventoryEvent.*;

public class ItemEvent implements ModInitializer {
    @Override
    public void onInitialize() {
        Enchantment DF = Enchantments.PROTECTION;
        //物品+效果
        ITEM_EFFECTS.put(Items.IRON_BLOCK, Pair.of(StatusEffects.RESISTANCE, 1));//铁块-抗性提升1
        ITEM_EFFECTS.put(Items.GOLD_BLOCK, Pair.of(StatusEffects.HEALTH_BOOST,2));//金块-生命提升
        ITEM_EFFECTS.put(Items.SUGAR, Pair.of(StatusEffects.SPEED,1));//糖-速度
        ITEM_EFFECTS.put(Items.BLAZE_ROD, Pair.of(StatusEffects.STRENGTH,1));//烈焰棒-力量
        ITEM_EFFECTS.put(Items.REDSTONE_BLOCK, Pair.of(StatusEffects.HASTE,2));//红石-急迫
        ITEM_EFFECTS.put(Items.GOLDEN_CARROT, Pair.of(StatusEffects.NIGHT_VISION,1));//金萝卜-夜视
        ITEM_EFFECTS.put(Items.TROPICAL_FISH, Pair.of(StatusEffects.LUCK,2));//热带鱼-幸运
        ITEM_EFFECTS.put(Items.ELYTRA, Pair.of(StatusEffects.SLOW_FALLING,1));//鞘翅-缓降
        ITEM_EFFECTS.put(Items.MAGMA_CREAM, Pair.of(StatusEffects.FIRE_RESISTANCE,1));//岩浆糕-防火
        ITEM_EFFECTS.put(Items.NETHER_STAR, Pair.of(StatusEffects.INSTANT_HEALTH,1));//下界之星-生命恢复
        ITEM_EFFECTS.put(Items.AMETHYST_BLOCK, Pair.of(StatusEffects.INVISIBILITY,1));//紫水晶-隐身
        ITEM_EFFECTS.put(Items.RABBIT_FOOT, Pair.of(StatusEffects.JUMP_BOOST,2));//兔子脚-跳跃提升

        //物品+附魔
        ITEM_ENCHANTMENTS.put(Items.IRON_BLOCK, DF);//铁块
        ITEM_ENCHANTMENTS.put(Items.GOLD_BLOCK, DF);//金块
        ITEM_ENCHANTMENTS.put(Items.SUGAR, DF);//糖
        ITEM_ENCHANTMENTS.put(Items.BLAZE_ROD, DF);//烈焰棒
        ITEM_ENCHANTMENTS.put(Items.REDSTONE_BLOCK, DF);//红石
        ITEM_ENCHANTMENTS.put(Items.GOLDEN_CARROT, DF);//金萝卜
        ITEM_ENCHANTMENTS.put(Items.TROPICAL_FISH, DF);//热带鱼
        ITEM_ENCHANTMENTS.put(Items.ELYTRA, DF);//鞘翅
        ITEM_ENCHANTMENTS.put(Items.MAGMA_CREAM, DF);//岩浆糕
        ITEM_ENCHANTMENTS.put(Items.NETHER_STAR, DF);//下界之星
        ITEM_ENCHANTMENTS.put(Items.AMETHYST_BLOCK, DF);//紫水晶
        ITEM_ENCHANTMENTS.put(Items.RABBIT_FOOT, DF);//兔子脚


    }



}
