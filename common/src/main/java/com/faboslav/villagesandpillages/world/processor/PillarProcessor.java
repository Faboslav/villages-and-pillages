package com.faboslav.villagesandpillages.world.processor;

import com.faboslav.villagesandpillages.init.VillagesAndPillagesProcessorTypes;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.StructureTemplate;
import net.minecraft.structure.processor.StructureProcessor;
import net.minecraft.structure.processor.StructureProcessorType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.ChunkRegion;
import net.minecraft.world.WorldView;

/**
 * Inspired by use in Better Fortresses mod
 *
 * @author YUNGNICKYOUNG
 * <a href="https://github.com/YUNG-GANG/YUNGs-Better-Fortresses">https://github.com/YUNG-GANG/YUNGs-Better-Fortresses</a>
 */
public final class PillarProcessor extends StructureProcessor
{
	public static final MapCodec<PillarProcessor> CODEC = RecordCodecBuilder.mapCodec(instance -> instance
		.group(
			BlockState.CODEC.fieldOf("target_block").forGetter(config -> config.targetBlock),
			BlockState.CODEC.fieldOf("target_block_output").forGetter(config -> config.targetBlockOutput),
			Direction.CODEC.optionalFieldOf("direction", Direction.DOWN).forGetter(processor -> processor.direction),
			Codec.INT.optionalFieldOf("pillar_length", -1).forGetter(config -> config.length))
		.apply(instance, instance.stable(PillarProcessor::new)));

	public final BlockState targetBlock;
	public final BlockState targetBlockOutput;
	public final Direction direction;
	public final int length;

	private PillarProcessor(
		BlockState targetBlock,
		BlockState targetBlockOutput,
		Direction direction,
		int length
	) {
		this.targetBlock = targetBlock;
		this.targetBlockOutput = targetBlockOutput;
		this.direction = direction;
		this.length = length;
	}

	@Override
	public StructureTemplate.StructureBlockInfo process(
		WorldView worldView,
		BlockPos pos,
		BlockPos pivot,
		StructureTemplate.StructureBlockInfo blockInfoLocal,
		StructureTemplate.StructureBlockInfo blockInfoGlobal,
		StructurePlacementData structurePlacementData
	) {
		if (blockInfoGlobal.state().isOf(this.targetBlock.getBlock())) {
			if (
				worldView instanceof ChunkRegion chunkRegion
				&& chunkRegion.getCenterPos().equals(new ChunkPos(blockInfoGlobal.pos())) == false
			) {
				return blockInfoGlobal;
			}

			blockInfoGlobal = new StructureTemplate.StructureBlockInfo(
				blockInfoGlobal.pos(),
				targetBlockOutput,
				blockInfoGlobal.nbt()
			);
			BlockPos.Mutable mutable = blockInfoGlobal.pos().mutableCopy().move(Direction.DOWN);
			BlockState currentBlockState = worldView.getBlockState(mutable);
			var worldBottomY = worldView.getBottomY();
			var worldTopY = worldView.getTopYInclusive();

			while (
				mutable.getY() > worldBottomY
				&& mutable.getY() < worldTopY
				&& (currentBlockState.isAir() || !worldView.getFluidState(mutable).isEmpty())
			) {
				worldView.getChunk(mutable).setBlockState(mutable, targetBlockOutput, false);
				mutable.move(Direction.DOWN);
				currentBlockState = worldView.getBlockState(mutable);
			}
		}

		return blockInfoGlobal;
	}

	protected StructureProcessorType<?> getType() {
		return VillagesAndPillagesProcessorTypes.PILLAR_PROCESSOR;
	}
}
