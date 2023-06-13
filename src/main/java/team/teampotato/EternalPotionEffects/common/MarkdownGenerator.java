package team.teampotato.EternalPotionEffects.common;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class MarkdownGenerator implements ModInitializer{
    @Override
    public void onInitialize() {
        File FileDir = new File(FabricLoader.getInstance().getConfigDir().toFile(), "EternalPotionEffects");
        if (!FileDir.exists()) {
            FileDir.mkdirs(); // 创建目录
        }
        GenMdConfig();
    }

    public static void GenMdConfig() {
        String markdownContent = generateMarkdownContent();


        try {
            FileWriter writer = new FileWriter("config/EternalPotionEffects/config-introduction.md");
            writer.write(markdownContent);
            writer.close();
            System.out.println("Markdown file generated successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String generateMarkdownContent() {
        StringBuilder sb = new StringBuilder();

        sb.append("# Configuration File Documentation\n\n");
        sb.append("This configuration file is used to set the effects and enchantments of items.\n\n");
        sb.append("## Configuration Items\n\n");
        sb.append("Each `item` entry in the configuration file represents an item and its corresponding effect and enchantment. Each configuration item has the following properties:\n\n");
        sb.append("- `item`: The identifier of the item, e.g., `minecraft:rabbit_foot`.\n");
        sb.append("- `effect`: The effect configuration of the item. It has the following properties:\n");
        sb.append("  - `effect`: The identifier of the effect, e.g., `minecraft:jump_boost`.\n");
        sb.append("  - `amplifier`: The amplification value of the effect.\n");
        sb.append("- `enchantment` (optional): The identifier of the enchantment for the item, e.g., `minecraft:sharpness`.\n\n");
        sb.append("## Example\n\n");
        sb.append("Here's an example of a configuration file:\n\n");
        sb.append("```json\n");
        sb.append("{\n");
        sb.append("  \"items\": [\n");
        sb.append("    {\n");
        sb.append("      \"item\": \"minecraft:rabbit_foot\",\n");
        sb.append("      \"effect\": {\n");
        sb.append("        \"effect\": \"minecraft:jump_boost\",\n");
        sb.append("        \"amplifier\": 2\n");
        sb.append("      }\n");
        sb.append("    },\n");
        sb.append("    {\n");
        sb.append("      \"item\": \"minecraft:diamond_sword\",\n");
        sb.append("      \"effect\": {\n");
        sb.append("        \"effect\": \"minecraft:strength\",\n");
        sb.append("        \"amplifier\": 1\n");
        sb.append("      },\n");
        sb.append("      \"enchantment\": \"minecraft:sharpness\"\n");
        sb.append("    }\n");
        sb.append("  ]\n");
        sb.append("}\n");
        sb.append("```\n\n");
        sb.append("The above example configures the effects and enchantments of two items. You can modify or add more configuration items according to your needs.\n\n");
        sb.append("Make sure to reload the game after modifying the configuration file for the changes to take effect.\n");

        return sb.toString();
    }
}
