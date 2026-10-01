package com.faboslav.villagesandpillages.forge;

import com.faboslav.villagesandpillages.common.VillagesAndPillages;
import com.faboslav.villagesandpillages.forge.platform.StructureProcessorTypeRegistryImpl;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(VillagesAndPillages.MOD_ID)
public final class VillagesAndPillagesForge
{
	public VillagesAndPillagesForge() {
		IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

		VillagesAndPillages.init();

		StructureProcessorTypeRegistryImpl.STRUCTURE_PROCESSOR_TYPES.register(modEventBus);
	}
}
