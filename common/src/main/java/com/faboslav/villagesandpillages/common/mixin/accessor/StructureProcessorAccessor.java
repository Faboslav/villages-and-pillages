package com.faboslav.villagesandpillages.common.mixin.accessor;

import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import org.spongepowered.asm.mixin.Mixin;

//? if < 26.2 {
/*import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import org.spongepowered.asm.mixin.gen.Invoker;
*///?}

@Mixin(StructureProcessor.class)
public interface StructureProcessorAccessor
{
	//? if < 26.2 {
	/*@Invoker("getType")
	StructureProcessorType<?> callGetType();
	*///?}
}
