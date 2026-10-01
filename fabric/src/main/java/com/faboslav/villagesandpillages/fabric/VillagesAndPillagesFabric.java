package com.faboslav.villagesandpillages.fabric;

import com.faboslav.villagesandpillages.common.VillagesAndPillages;
import net.fabricmc.api.ModInitializer;

public final class VillagesAndPillagesFabric implements ModInitializer
{
	@Override
	public void onInitialize() {
		VillagesAndPillages.init();
	}
}
