package com.faboslav.villagesandpillages.forge.platform;

import com.faboslav.villagesandpillages.common.VillagesAndPillages;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraftforge.registries.DeferredRegister;

public class StructureProcessorTypeRegistryImpl
{
	public static final DeferredRegister<StructureProcessorType<?>> STRUCTURE_PROCESSOR_TYPES = DeferredRegister.create(Registries.STRUCTURE_PROCESSOR, VillagesAndPillages.MOD_ID);

	public static void registerStructureProcessorType(
		String name,
		StructureProcessorType<? extends StructureProcessor> structureProcessorType
	) {
		STRUCTURE_PROCESSOR_TYPES.register(name, () -> structureProcessorType);
	}
}
