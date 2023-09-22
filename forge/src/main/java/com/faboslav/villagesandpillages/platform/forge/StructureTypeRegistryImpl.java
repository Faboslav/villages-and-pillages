package com.faboslav.villagesandpillages.platform.forge;

import com.faboslav.villagesandpillages.VillagesAndPillages;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.world.gen.structure.Structure;
import net.minecraft.world.gen.structure.StructureType;
import net.minecraftforge.registries.DeferredRegister;

public class StructureTypeRegistryImpl
{
	public static final DeferredRegister<StructureType<?>> STRUCTURE_TYPES = DeferredRegister.create(RegistryKeys.STRUCTURE_TYPE, VillagesAndPillages.MOD_ID);

	public static <T extends Structure> void registerStructureType(
		String name,
		StructureType<T> structureType
	) {
		STRUCTURE_TYPES.register(name, () -> structureType);
	}
}
