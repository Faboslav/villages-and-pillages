package com.faboslav.villagesandpillages.common;

import com.faboslav.villagesandpillages.common.init.VillagesAndPillagesProcessorTypes;
import com.faboslav.villagesandpillages.common.tag.VillagesAndPillagesTags;
import net.minecraft.resources.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class VillagesAndPillages
{
	public static final String MOD_ID = "villagesandpillages";
	private static final Logger LOGGER = LoggerFactory.getLogger(VillagesAndPillages.MOD_ID);

	public static Identifier makeId(String path) {
		//? if >= 1.21 {
		return Identifier.tryBuild(
			MOD_ID,
			path
		);
		//?} else {
		/*return new Identifier(
			MOD_ID,
			path
		);
		*///?}
	}

	public static Logger getLogger() {
		return LOGGER;
	}

	public static void init() {
		VillagesAndPillagesTags.init();

		VillagesAndPillagesProcessorTypes.init();
	}
}
