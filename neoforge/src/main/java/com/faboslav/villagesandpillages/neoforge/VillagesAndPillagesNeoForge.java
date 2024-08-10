package com.faboslav.villagesandpillages.neoforge;

import com.faboslav.villagesandpillages.VillagesAndPillages;
import com.faboslav.villagesandpillages.platform.neoforge.StructureProcessorTypeRegistryImpl;
import com.faboslav.villagesandpillages.platform.neoforge.StructureTypeRegistryImpl;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

@Mod(VillagesAndPillages.MOD_ID)
public final class VillagesAndPillagesNeoForge
{
	public VillagesAndPillagesNeoForge(ModContainer modContainer, IEventBus modEventBus) {
		VillagesAndPillages.init();

		StructureProcessorTypeRegistryImpl.STRUCTURE_PROCESSOR_TYPES.register(modEventBus);
		StructureTypeRegistryImpl.STRUCTURE_TYPES.register(modEventBus);
	}
}
