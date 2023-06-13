package team.teampotato.EternalPotionEffects;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EternalPotionEffects implements ModInitializer {
	public static String mod_id = "eternalpotioneffects";
	public static String MOD_NAME = "Eternal Potion Effects";
	public static final Logger LOGGER = LoggerFactory.getLogger(mod_id);


	@Override
	public void onInitialize() {
		LOGGER.info(MOD_NAME + " Load!");
	}
}
