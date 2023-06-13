package team.teampotato.EternalPotionEffects.Event;


import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.apache.commons.lang3.tuple.Pair;
import net.minecraft.server.network.ServerPlayerEntity;
import team.teampotato.EternalPotionEffects.common.Config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class InventoryEvent implements ModInitializer {

    private static int ticks = 0;
    public static final Map<Item, Pair<StatusEffect, Integer>> ITEM_EFFECTS = new HashMap<>();
    public static final Map<Item, Enchantment> ITEM_ENCHANTMENTS = new HashMap<>();

    @Override
    public void onInitialize() {
        Map<Item, Pair<StatusEffect, Integer>> itemEffects = Config.getItemEffects();
        Map<Item, Enchantment> itemEnchantments = Config.getItemEnchantments();
        // 添加自定义物品的药水效果
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            ticks++;
            if (ticks % 20 == 0) { // 每秒钟检测一次
                server.getPlayerManager().getPlayerList().forEach(player -> checkItems(player, itemEffects, itemEnchantments, 5));
                server.getPlayerManager().getPlayerList().forEach(player -> checkItems(player, ITEM_EFFECTS, ITEM_ENCHANTMENTS, 5));
            }
        });
    }

    public static void checkItems(ServerPlayerEntity player, Map<Item, Pair<StatusEffect, Integer>> itemEffects, Map<Item, Enchantment> itemEnchantments, int effectDuration) {
        if (!player.getWorld().isClient) {
            List<ItemStack> items = player.getInventory().main;
            int itemCount = items.stream()
                    .filter(itemStack -> itemEffects.containsKey(itemStack.getItem()))
                    .mapToInt(ItemStack::getCount)
                    .sum();

            itemEffects.keySet().forEach(item -> {
                int itemStackCount = items.stream()
                        .filter(itemStack -> itemStack.getItem() == item)
                        .mapToInt(ItemStack::getCount)
                        .sum();

                if (itemStackCount >= 30) {
                    items.stream()
                            .filter(itemStack -> itemStack.getItem() == item)
                            .forEach(itemStack -> {
                                if (!itemStack.hasEnchantments()) {
                                    itemStack.addEnchantment(itemEnchantments.get(item), 0);
                                }
                            });

                    Pair<StatusEffect, Integer> effectPair = itemEffects.get(item);
                    StatusEffect effect = effectPair.getLeft();
                    int effectAmplifier = effectPair.getRight();

                    player.addStatusEffect(new StatusEffectInstance(effect, effectDuration * 20, effectAmplifier - 1));
                } else {
                    items.stream()
                            .filter(itemStack -> itemStack.getItem() == item)
                            .forEach(itemStack -> {
                                if (itemStack.hasEnchantments()) {
                                    itemStack.removeSubNbt("Enchantments");
                                }
                            });

                    player.removeStatusEffect(itemEffects.get(item).getLeft());
                }
            });
        }
    }
}