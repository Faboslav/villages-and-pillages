package com.faboslav.villagesandpillages.fabric;

import com.faboslav.villagesandpillages.VillagesAndPillages;
import net.fabricmc.api.ModInitializer;

public final class VillagesAndPillagesFabric implements ModInitializer
{
	@Override
	public void onInitialize() {
		VillagesAndPillages.init();
		VillagesAndPillages.postInit();
	}
}
