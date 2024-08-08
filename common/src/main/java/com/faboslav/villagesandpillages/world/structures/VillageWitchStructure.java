package com.faboslav.villagesandpillages.world.structures;

import com.faboslav.villagesandpillages.init.VillagesAndPillagesStructureTypes;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.yungnickyoung.minecraft.yungsapi.api.YungJigsawManager;
import com.yungnickyoung.minecraft.yungsapi.world.structure.YungJigsawStructure;
import com.yungnickyoung.minecraft.yungsapi.world.structure.terrainadaptation.EnhancedTerrainAdaptation;
import com.yungnickyoung.minecraft.yungsapi.world.structure.terrainadaptation.EnhancedTerrainAdaptationType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.structure.StructureLiquidSettings;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeCoords;
import net.minecraft.world.gen.HeightContext;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.VerticalBlockSample;
import net.minecraft.world.gen.heightprovider.HeightProvider;
import net.minecraft.world.gen.noise.NoiseConfig;
import net.minecraft.world.gen.structure.DimensionPadding;
import net.minecraft.world.gen.structure.JigsawStructure;
import net.minecraft.world.gen.structure.Structure;
import net.minecraft.world.gen.structure.StructureType;

import java.util.Optional;
import java.util.function.Predicate;

/**
 * Inspired by use in Repurposed Structures mod
 *
 * @author TelepathicGrunt
 * <a href="https://github.com/TelepathicGrunt/RepurposedStructures">https://github.com/TelepathicGrunt/RepurposedStructures</a>
 */
public class VillageWitchStructure extends YungJigsawStructure
{
	public static final int MAX_TOTAL_STRUCTURE_RADIUS = 128;
	public static final MapCodec<VillageWitchStructure> CODEC = RecordCodecBuilder.mapCodec(builder -> builder
			.group(
				configCodecBuilder(builder),
				StructurePool.REGISTRY_CODEC.fieldOf("start_pool").forGetter(structure -> structure.startPool),
				Identifier.CODEC.optionalFieldOf("start_jigsaw_name").forGetter(structure -> structure.startJigsawName),
				Codec.intRange(0, 128).fieldOf("size").forGetter(structure -> structure.maxDepth),
				HeightProvider.CODEC.fieldOf("start_height").forGetter(structure -> structure.startHeight),
				IntProvider.createValidatingCodec(0, 15).optionalFieldOf("x_offset_in_chunk", ConstantIntProvider.create(0)).forGetter(structure -> structure.xOffsetInChunk),
				IntProvider.createValidatingCodec(0, 15).optionalFieldOf("z_offset_in_chunk", ConstantIntProvider.create(0)).forGetter(structure -> structure.zOffsetInChunk),
				Codec.BOOL.optionalFieldOf("use_expansion_hack", false).forGetter(structure -> structure.useExpansionHack),
				Heightmap.Type.CODEC.optionalFieldOf("project_start_to_heightmap").forGetter(structure -> structure.projectStartToHeightmap),
				Codec.intRange(1, MAX_TOTAL_STRUCTURE_RADIUS).fieldOf("max_distance_from_center").forGetter(structure -> structure.maxDistanceFromCenter),
				Codec.INT.optionalFieldOf("max_y").forGetter(structure -> structure.maxY),
				Codec.INT.optionalFieldOf("min_y").forGetter(structure -> structure.minY),
				EnhancedTerrainAdaptationType.ADAPTATION_CODEC.optionalFieldOf("enhanced_terrain_adaptation", EnhancedTerrainAdaptation.NONE).forGetter(structure -> structure.enhancedTerrainAdaptation),
				DimensionPadding.CODEC.optionalFieldOf("dimension_padding", DimensionPadding.NONE).forGetter(structure -> structure.dimensionPadding),
				StructureLiquidSettings.codec.optionalFieldOf("liquid_settings", StructureLiquidSettings.APPLY_WATERLOGGING).forGetter(structure -> structure.liquidSettings))
			.apply(builder, VillageWitchStructure::new));

	public final RegistryEntry<StructurePool> startPool;
	private final Optional<Identifier> startJigsawName;
	public final int maxDepth;
	public final HeightProvider startHeight;
	public final IntProvider xOffsetInChunk;
	public final IntProvider zOffsetInChunk;
	public final boolean useExpansionHack;
	public final Optional<Heightmap.Type> projectStartToHeightmap;
	public final int maxDistanceFromCenter;
	public final Optional<Integer> maxY;
	public final Optional<Integer> minY;
	public final EnhancedTerrainAdaptation enhancedTerrainAdaptation;
	public final DimensionPadding dimensionPadding;
	public final StructureLiquidSettings liquidSettings;

	public VillageWitchStructure(
		Structure.Config structureSettings,
		RegistryEntry<StructurePool> startPool,
		Optional<Identifier> startJigsawName,
		int maxDepth,
		HeightProvider startHeight,
		IntProvider xOffsetInChunk,
		IntProvider zOffsetInChunk,
		boolean useExpansionHack,
		Optional<Heightmap.Type> projectStartToHeightmap,
		int maxBlockDistanceFromCenter,
		Optional<Integer> maxY,
		Optional<Integer> minY,
		EnhancedTerrainAdaptation enhancedTerrainAdaptation,
		DimensionPadding dimensionPadding,
		StructureLiquidSettings liquidSettings
	) {
		super(
			structureSettings,
			startPool,
			startJigsawName,
			maxDepth,
			startHeight,
			xOffsetInChunk,
			zOffsetInChunk,
			useExpansionHack,
			projectStartToHeightmap,
			maxBlockDistanceFromCenter,
			maxY,
			minY,
			enhancedTerrainAdaptation,
			dimensionPadding,
			liquidSettings
		);
		this.startPool = startPool;
		this.startJigsawName = startJigsawName;
		this.maxDepth = maxDepth;
		this.startHeight = startHeight;
		this.xOffsetInChunk = xOffsetInChunk;
		this.zOffsetInChunk = zOffsetInChunk;
		this.useExpansionHack = useExpansionHack;
		this.projectStartToHeightmap = projectStartToHeightmap;
		this.maxDistanceFromCenter = maxBlockDistanceFromCenter;
		this.maxY = maxY;
		this.minY = minY;
		this.enhancedTerrainAdaptation = enhancedTerrainAdaptation;
		this.dimensionPadding = dimensionPadding;
		this.liquidSettings = liquidSettings;
	}

	@Override
	public Optional<StructurePosition> getStructurePosition(Context context) {
		int offsetY = this.startHeight.get(context.random(), new HeightContext(context.chunkGenerator(), context.world()));
		BlockPos blockPos = new BlockPos(context.chunkPos().getStartX(), offsetY, context.chunkPos().getStartZ());

		if (extraSpawningChecks(context, blockPos) == false) {
			return Optional.empty();
		}

		ChunkPos chunkPos = context.chunkPos();
		Random randomSource = context.random();
		int xOffset = this.xOffsetInChunk.get(randomSource);
		int zOffset = this.zOffsetInChunk.get(randomSource);
		int startY = this.startHeight.get(context.random(), new HeightContext(context.chunkGenerator(), context.world()));
		BlockPos startPos = new BlockPos(chunkPos.getOffsetX(xOffset), startY, chunkPos.getOffsetZ(zOffset));

		return YungJigsawManager.assembleJigsawStructure(
			context,
			this.startPool,
			this.startJigsawName,
			this.maxDepth,
			startPos,
			this.useExpansionHack,
			this.projectStartToHeightmap,
			this.maxDistanceFromCenter,
			this.maxY,
			this.minY,
			JigsawStructure.DEFAULT_DIMENSION_PADDING,
			JigsawStructure.DEFAULT_LIQUID_SETTINGS
		);
	}

	private boolean extraSpawningChecks(Context context, BlockPos blockPos) {
		int checkRadius = 64;
		BlockPos.Mutable mutable = new BlockPos.Mutable();
		int negativeFluidChecks = 0;

		for (int xOffset = -checkRadius; xOffset <= checkRadius; xOffset += 8) {
			for (int zOffset = -checkRadius; zOffset <= checkRadius; zOffset += 8) {
				int x = xOffset + blockPos.getX();
				int z = zOffset + blockPos.getZ();

				if (xOffset % checkRadius == 0 && zOffset % checkRadius == 0) {
					var structurePosition = new StructurePosition(new BlockPos(x, 68, z), collector -> {
					});

					if (!this.isBiomeValid(
						structurePosition,
						context.chunkGenerator(),
						context.noiseConfig(),
						this.getValidBiomes()::contains
					)) {
						return false;
					}
				}

				VerticalBlockSample blockView = context.chunkGenerator().getColumnSample(
					x,
					z,
					context.world(),
					context.noiseConfig()
				);

				mutable.set(blockPos).move(xOffset, -6, zOffset);

				if (this.isViableBlockState(blockView.getState(mutable.getY()))) {
					negativeFluidChecks++;

					// More than third of total blocks are negative check
					if (negativeFluidChecks > 64) {
						return false;
					}
				}
			}
		}

		return true;
	}

	private boolean isViableBlockState(BlockState blockState) {
		if (
			blockState.getFluidState().isEmpty()
			|| blockState.isOf(Blocks.AIR) == false) {
			return false;
		}

		return true;
	}

	private boolean isBiomeValid(
		StructurePosition result,
		ChunkGenerator chunkGenerator,
		NoiseConfig noiseConfig,
		Predicate<RegistryEntry<Biome>> validBiomes
	) {
		BlockPos blockPos = result.position();
		return validBiomes.test(chunkGenerator.getBiomeSource().getBiome(BiomeCoords.fromBlock(blockPos.getX()), BiomeCoords.fromBlock(blockPos.getY()), BiomeCoords.fromBlock(blockPos.getZ()), noiseConfig.getMultiNoiseSampler()));
	}

	@Override
	public StructureType<?> getType() {
		return VillagesAndPillagesStructureTypes.VILLAGE_WITCH_STRUCTURE;
	}
}