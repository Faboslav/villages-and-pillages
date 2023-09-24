package com.faboslav.villagesandpillages.platform.forge;

import com.faboslav.villagesandpillages.VillagesAndPillages;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.structure.processor.StructureProcessor;
import net.minecraft.structure.processor.StructureProcessorType;
import net.minecraftforge.registries.DeferredRegister;

public class StructureProcessorTypeRegistryImpl
{
	public static final DeferredRegister<StructureProcessorType<?>> STRUCTURE_PROCESSOR_TYPES = DeferredRegister.create(RegistryKeys.STRUCTURE_PROCESSOR, VillagesAndPillages.MOD_ID);

	public static void registerStructureProcessorType(
		String name,
		StructureProcessorType<? extends StructureProcessor> structureProcessorType
	) {
		STRUCTURE_PROCESSOR_TYPES.register(name, () -> structureProcessorType);
	}
}
