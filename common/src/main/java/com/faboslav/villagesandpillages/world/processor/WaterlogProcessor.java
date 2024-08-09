package com.faboslav.villagesandpillages.world.processor;

import com.faboslav.villagesandpillages.init.VillagesAndPillagesProcessorTypes;
import com.mojang.serialization.MapCodec;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.StructureTemplate;
import net.minecraft.structure.processor.StructureProcessor;
import net.minecraft.structure.processor.StructureProcessorType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldView;

public class WaterlogProcessor extends StructureProcessor
{
	public static final MapCodec<WaterlogProcessor> CODEC = MapCodec.unit(WaterlogProcessor::new);

	@Override
	public StructureTemplate.StructureBlockInfo process(
		WorldView world,
		BlockPos pos,
		BlockPos pivot,
		StructureTemplate.StructureBlockInfo originalBlockInfo,
		StructureTemplate.StructureBlockInfo currentBlockInfo,
		StructurePlacementData structurePlacementData
	) {
		return currentBlockInfo;
	}

	@Override
	protected StructureProcessorType<?> getType() {
		return VillagesAndPillagesProcessorTypes.WATERLOG_PROCESSOR;
	}
}
