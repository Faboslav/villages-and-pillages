package com.faboslav.villagesandpillages.world.structures;

import com.faboslav.villagesandpillages.VillagesAndPillages;
import com.faboslav.villagesandpillages.init.VillagesAndPillagesStructureTypes;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.yungnickyoung.minecraft.yungsapi.world.structure.YungJigsawStructure;
import com.yungnickyoung.minecraft.yungsapi.world.structure.terrainadaptation.EnhancedTerrainAdaptation;
import com.yungnickyoung.minecraft.yungsapi.world.structure.terrainadaptation.EnhancedTerrainAdaptationType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolBasedGenerator;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeCoords;
import net.minecraft.world.gen.HeightContext;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.VerticalBlockSample;
import net.minecraft.world.gen.heightprovider.HeightProvider;
import net.minecraft.world.gen.noise.NoiseConfig;
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
	public static final Codec<VillageWitchStructure> CODEC = RecordCodecBuilder.create(builder -> builder.group(configCodecBuilder(builder), StructurePool.REGISTRY_CODEC.fieldOf("start_pool").forGetter((structure) -> {
		return structure.startPool;
	}), Identifier.CODEC.optionalFieldOf("start_jigsaw_name").forGetter((structure) -> {
		return structure.startJigsawName;
	}), Codec.intRange(0, 128).fieldOf("size").forGetter((structure) -> {
		return structure.maxDepth;
	}), HeightProvider.CODEC.fieldOf("start_height").forGetter((structure) -> {
		return structure.startHeight;
	}), IntProvider.createValidatingCodec(0, 15).optionalFieldOf("x_offset_in_chunk", ConstantIntProvider.create(0)).forGetter((structure) -> {
		return structure.xOffsetInChunk;
	}), IntProvider.createValidatingCodec(0, 15).optionalFieldOf("z_offset_in_chunk", ConstantIntProvider.create(0)).forGetter((structure) -> {
		return structure.zOffsetInChunk;
	}), Codec.BOOL.optionalFieldOf("use_expansion_hack", false).forGetter((structure) -> {
		return structure.useExpansionHack;
	}), Heightmap.Type.CODEC.optionalFieldOf("project_start_to_heightmap").forGetter((structure) -> {
		return structure.projectStartToHeightmap;
	}), Codec.intRange(1, MAX_TOTAL_STRUCTURE_RADIUS).fieldOf("max_distance_from_center").forGetter((structure) -> {
		return structure.maxDistanceFromCenter;
	}), Codec.INT.optionalFieldOf("max_y").forGetter((structure) -> {
		return structure.maxY;
	}), Codec.INT.optionalFieldOf("min_y").forGetter((structure) -> {
		return structure.minY;
	}), EnhancedTerrainAdaptationType.ADAPTATION_CODEC.optionalFieldOf("enhanced_terrain_adaptation", EnhancedTerrainAdaptation.NONE).forGetter((structure) -> {
		return structure.enhancedTerrainAdaptation;
	})).apply(builder, VillageWitchStructure::new));

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
		EnhancedTerrainAdaptation enhancedTerrainAdaptation
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
			enhancedTerrainAdaptation
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

		VillagesAndPillages.getLogger().info(startHeight.toString());
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

		return StructurePoolBasedGenerator.generate(
			context,
			this.startPool,
			this.startJigsawName,
			this.maxDepth,
			startPos,
			false,
			this.projectStartToHeightmap,
			this.maxDistanceFromCenter
		);
	}

	private boolean extraSpawningChecks(Context context, BlockPos blockPos) {
		/*
		if(context.chunkGenerator().shouldStructureGenerateInRange(RegistryEntry.of(StructureSets.initAndGetDefault()), context.noiseConfig(), context.seed(), blockPos.getX(), blockPos.getZ(), 128)) {

		}*/

		int checkRadius = 64;
		BlockPos.Mutable mutable = new BlockPos.Mutable();
		int negativeFluidChecks = 0;

		for (int xOffset = -checkRadius; xOffset <= checkRadius; xOffset += 8) {
			for (int zOffset = -checkRadius; zOffset <= checkRadius; zOffset += 8) {
				int x = xOffset + blockPos.getX();
				int z = zOffset + blockPos.getZ();
				if(xOffset % checkRadius == 0 && zOffset % checkRadius == 0) {
					//VillagesAndPillages.getLogger().info("structure biome check: " + new BlockPos(x, 68, z).toShortString());
					var structurePosition = new StructurePosition(new BlockPos(x, 68, z), collector -> {});

					if (this.isBiomeValid(
						structurePosition,
						context.chunkGenerator(),
						context.noiseConfig(),
						this.getValidBiomes()::contains
					) == false) {
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

				if (
					this.isViableBlockState(blockView.getState(mutable.getY()))
					//&& this.isViableBlockState(blockView.getState(mutable.move(Direction.UP).getY()))
					//&& this.isViableBlockState(blockView.getState(mutable.move(Direction.UP, 2).getY()))
				) {
					negativeFluidChecks++;

					// More than half blocks are negative check
					if (negativeFluidChecks > 96) {
						return false;
					}
				}
			}
		}

		VillagesAndPillages.getLogger().info("da");

		return true;
	}

	private boolean isViableBlockState(BlockState blockState) {
		if(
			blockState.getFluidState().isEmpty()
		   	|| blockState.isOf(Blocks.AIR) == false)
		{
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