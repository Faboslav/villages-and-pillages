package com.faboslav.villagesandpillages.platform.forge;

import com.faboslav.villagesandpillages.VillagesAndPillages;
import net.minecraft.structure.processor.StructureProcessor;
import net.minecraft.structure.processor.StructureProcessorType;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.registries.DeferredRegister;

public class StructureProcessorTypeRegistryImpl
{
	public static final DeferredRegister<StructureProcessorType<?>> STRUCTURE_PROCESSOR_TYPES = DeferredRegister.create(Registry.STRUCTURE_PROCESSOR_KEY, VillagesAndPillages.MOD_ID);

	public static void registerStructureProcessorType(
		String name,
		StructureProcessorType<? extends StructureProcessor> structureProcessorType
	) {
		STRUCTURE_PROCESSOR_TYPES.register(name, () -> structureProcessorType);
	}
}
