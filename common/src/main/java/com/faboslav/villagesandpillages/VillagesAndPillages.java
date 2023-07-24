package com.faboslav.villagesandpillages;

import com.faboslav.villagesandpillages.init.VillagesAndPillagesProcessorTypes;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class VillagesAndPillages
{
	public static final String MOD_ID = "villagesandpillages";
	private static final Logger LOGGER = LoggerFactory.getLogger(VillagesAndPillages.MOD_ID);

	public static Identifier makeID(String path) {
		return new Identifier(
			MOD_ID,
			path
		);
	}

	public static Logger getLogger() {
		return LOGGER;
	}

	public static void init() {
		VillagesAndPillagesProcessorTypes.init();
	}

	public static void postInit() {
		VillagesAndPillagesProcessorTypes.postInit();
	}
}
