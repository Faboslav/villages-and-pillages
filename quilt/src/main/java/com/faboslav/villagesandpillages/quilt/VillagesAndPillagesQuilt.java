package com.faboslav.villagesandpillages.quilt;

import com.faboslav.villagesandpillages.VillagesAndPillages;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;

public final class VillagesAndPillagesQuilt implements ModInitializer
{
	@Override
	public void onInitialize(ModContainer mod) {
		VillagesAndPillages.init();
	}
}
