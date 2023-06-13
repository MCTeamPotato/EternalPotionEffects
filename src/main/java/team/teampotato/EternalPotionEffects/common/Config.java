package team.teampotato.EternalPotionEffects.common;

import net.fabricmc.api.ModInitializer;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;

import com.google.gson.Gson;
import net.minecraft.entity.effect.StatusEffect;

import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import org.apache.commons.lang3.tuple.Pair;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

import static team.teampotato.EternalPotionEffects.Event.InventoryEvent.*;


public class Config implements ModInitializer {

    private static final Gson gson = new Gson();
    public static Map<Item, Pair<StatusEffect, Integer>> getItemEffects() {
        return ITEM_EFFECTS;
    }

    public static Map<Item, Enchantment> getItemEnchantments() {
        return ITEM_ENCHANTMENTS;
    }


    @Override
    public void onInitialize() {
        loadConfig();
    }

    private void loadConfig() {
        try {
            File configDir = new File("config" + File.separator + "EternalPotionEffects");
            if (!configDir.exists()) {
                configDir.mkdirs();
                generateConfig();
            }
            File configFile = new File(configDir, "config.json");
            if (configFile.exists()) {
                JsonReader reader = new JsonReader(new FileReader(configFile));
                JsonObject configJson = gson.fromJson(reader, JsonObject.class);
                if (configJson.has("items") && configJson.get("items").isJsonArray()) {
                    JsonArray itemsArray = configJson.getAsJsonArray("items");
                    for (JsonElement itemElement : itemsArray) {
                        if (itemElement.isJsonObject()) {
                            JsonObject itemObject = itemElement.getAsJsonObject();
                            //if (itemObject.has("item") && itemObject.has("effects") && itemObject.has("amplifier")) {
                            if (itemObject.has("item")) {
                                Identifier itemId = new Identifier(itemObject.get("item").getAsString());
                                Item item = Registries.ITEM.get(itemId);
                                JsonObject effectObject = itemObject.getAsJsonObject("effect");
                                String effectName = effectObject.get("effect").getAsString();
                                int amplifier = effectObject.get("amplifier").getAsInt();
                                StatusEffect effect = Registries.STATUS_EFFECT.get(new Identifier(effectName));
                                getItemEffects().put(item, Pair.of(effect, amplifier));
                                /*
                                EternalPotionEffects.LOGGER.info("ItemID: " + itemId);
                                EternalPotionEffects.LOGGER.info("Item: " + item);
                                EternalPotionEffects.LOGGER.info("Effect: " + effectName);
                                EternalPotionEffects.LOGGER.info("Amplifier: " + amplifier);
                                 */
                            }
                            if (itemObject.has("enchantment")) {
                                String enchantmentName = itemObject.get("enchantment").getAsString();
                                Identifier enchantmentId = new Identifier(enchantmentName);
                                Enchantment enchantment = Registries.ENCHANTMENT.getOrEmpty(enchantmentId).orElse(null);
                                String itemName = itemObject.get("item").getAsString();
                                Item item = Registries.ITEM.get(new Identifier(itemName));
                                getItemEnchantments().put(item, enchantment);
                            }
                        }
                    }
                }
            } else {
                generateConfig();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void generateConfig() {
        try {
            File configFile = new File("config" + File.separator + "EternalPotionEffects" + File.separator + "config.json");
            JsonObject configJson = new JsonObject();
            JsonArray itemsArray = new JsonArray();
            for (Map.Entry<Item, Pair<StatusEffect, Integer>> entry : ITEM_EFFECTS.entrySet()) {
                JsonObject itemObject = new JsonObject();
                Item item = entry.getKey();
                Pair<StatusEffect, Integer> effectPair = entry.getValue();

                itemObject.addProperty("item", Registries.ITEM.getId(item).toString());

                JsonObject effectObject = new JsonObject();
                effectObject.addProperty("effect", Objects.requireNonNull(Registries.STATUS_EFFECT.getId(effectPair.getLeft())).toString());
                effectObject.addProperty("amplifier", effectPair.getRight());
                itemObject.add("effect", effectObject);

                Enchantment enchantment = ITEM_ENCHANTMENTS.get(item);
                if (enchantment != null) {
                    itemObject.addProperty("enchantment", Objects.requireNonNull(Registries.ENCHANTMENT.getId(enchantment)).toString());
                }

                itemsArray.add(itemObject);
            }
            configJson.add("items", itemsArray);

            // 将 JSON 对象写入配置文件
            FileWriter writer = new FileWriter(configFile);
            gson.toJson(configJson, writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
