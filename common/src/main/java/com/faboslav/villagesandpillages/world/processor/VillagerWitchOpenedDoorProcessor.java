package com.faboslav.villagesandpillages.world.processor;

import com.faboslav.villagesandpillages.init.VillagesAndPillagesProcessorTypes;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.StructureTemplate;
import net.minecraft.structure.processor.StructureProcessor;
import net.minecraft.structure.processor.StructureProcessorType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.WorldView;

public final class VillagerWitchOpenedDoorProcessor extends StructureProcessor
{
	public static final MapCodec<VillagerWitchOpenedDoorProcessor> CODEC = MapCodec.unit(VillagerWitchOpenedDoorProcessor::new);

	@Override
	public StructureTemplate.StructureBlockInfo process(
		WorldView worldView,
		BlockPos pos,
		BlockPos pivot,
		StructureTemplate.StructureBlockInfo originalBlockInfo,
		StructureTemplate.StructureBlockInfo currentBlockInfo,
		StructurePlacementData structurePlacementData
	) {
		Block block = currentBlockInfo.state().getBlock();
		if (
			block instanceof DoorBlock == false
			|| block.getDefaultMapColor() != Blocks.SPRUCE_PLANKS.getDefaultMapColor()
		) {
			return currentBlockInfo;
		}

		BlockPos.Mutable doorBlockPos = currentBlockInfo.pos().mutableCopy();
		BlockState doorBlockState = currentBlockInfo.state();
		DoubleBlockHalf doubleBlockHalf = doorBlockState.get(DoorBlock.HALF);

		BlockPos.Mutable lowerDoorBlockPos = doorBlockPos.mutableCopy();

		if (doubleBlockHalf == DoubleBlockHalf.UPPER) {
			lowerDoorBlockPos = lowerDoorBlockPos.move(Direction.DOWN);
		}

		Random random = structurePlacementData.getRandom(lowerDoorBlockPos);
		boolean isOpened = random.nextFloat() > 0.66F;

		currentBlockInfo = new StructureTemplate.StructureBlockInfo(
			doorBlockPos,
			doorBlockState.with(DoorBlock.OPEN, isOpened),
			null
		);

		return currentBlockInfo;
	}

	protected StructureProcessorType<?> getType() {
		return VillagesAndPillagesProcessorTypes.VILLAGE_WITCH_OPENED_DOOR_PROCESSOR;
	}
}
