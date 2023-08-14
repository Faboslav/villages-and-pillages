package com.faboslav.villagesandpillages.world.processor;

import com.faboslav.villagesandpillages.VillagesAndPillages;
import com.faboslav.villagesandpillages.init.VillagesAndPillagesProcessorTypes;
import com.mojang.serialization.Codec;
import com.yungnickyoung.minecraft.yungsapi.world.BlockStateRandomizer;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.StructureTemplate;
import net.minecraft.structure.processor.StructureProcessor;
import net.minecraft.structure.processor.StructureProcessorType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.WorldView;

public class VillageWitchFlowerPotProcessor extends StructureProcessor
{
	public static final VillageWitchFlowerPotProcessor INSTANCE = new VillageWitchFlowerPotProcessor();
	public static final Codec<VillageWitchFlowerPotProcessor> CODEC = Codec.unit(() -> INSTANCE);
	private final BlockStateRandomizer pottedPlantsSelector = new BlockStateRandomizer(Blocks.FLOWER_POT.getDefaultState())
		.addBlock(Blocks.POTTED_DEAD_BUSH.getDefaultState(), 0.3f)
		.addBlock(Blocks.POTTED_BLUE_ORCHID.getDefaultState(), 0.15f)
		.addBlock(Blocks.AZALEA.getDefaultState(), 0.15f)
		.addBlock(Blocks.POTTED_BROWN_MUSHROOM.getDefaultState(), 0.15f)
		.addBlock(Blocks.POTTED_RED_MUSHROOM.getDefaultState(), 0.15f);

	@Override
	public StructureTemplate.StructureBlockInfo process(
		WorldView world,
		BlockPos pos,
		BlockPos pivot,
		StructureTemplate.StructureBlockInfo originalBlockInfo,
		StructureTemplate.StructureBlockInfo currentBlockInfo,
		StructurePlacementData structurePlacementData
	) {
		if (currentBlockInfo.state.getBlock() != Blocks.FLOWER_POT) {
			return currentBlockInfo;
		}

		Random random = structurePlacementData.getRandom(currentBlockInfo.pos);

		currentBlockInfo = new StructureTemplate.StructureBlockInfo(
			currentBlockInfo.pos,
			pottedPlantsSelector.get(random),
			null
		);

		return currentBlockInfo;
	}

	@Override
	protected StructureProcessorType<?> getType() {
		return VillagesAndPillagesProcessorTypes.VILLAGE_WITCH_FLOWER_POT_PROCESSOR;
	}
}