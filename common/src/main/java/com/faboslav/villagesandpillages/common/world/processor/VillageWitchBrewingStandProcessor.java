package com.faboslav.villagesandpillages.common.world.processor;

import com.faboslav.villagesandpillages.common.versions.VersionedNbt;
import com.mojang.serialization.MapCodec;
import net.minecraft.util.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;

//? if < 26.2 {
/*import com.faboslav.villagesandpillages.common.init.VillagesAndPillagesProcessorTypes;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
*///?}

//? if >= 26.2 {
public class VillageWitchBrewingStandProcessor implements StructureProcessor
//?} else {
/*public class VillageWitchBrewingStandProcessor extends StructureProcessor
*///?}
{
	public static final MapCodec<VillageWitchBrewingStandProcessor> CODEC = MapCodec.unit(VillageWitchBrewingStandProcessor::new);

	@Override
	public StructureTemplate.StructureBlockInfo processBlock(
		LevelReader levelReader,
		BlockPos pos,
		BlockPos pivot,
		//? if >= 26.2 {
		BlockPos templateRelativePos,
		//?} else {
		/*StructureTemplate.StructureBlockInfo originalBlockInfo,
		*///?}
		StructureTemplate.StructureBlockInfo currentBlockInfo,
		StructurePlaceSettings structurePlaceSettings
	) {
		if (!currentBlockInfo.state().is(Blocks.BREWING_STAND)) {
			return currentBlockInfo;
		}

		RandomSource random = structurePlaceSettings.getRandom(currentBlockInfo.pos());
		CompoundTag nbt = currentBlockInfo.nbt();
		ListTag itemsListNbt = VersionedNbt.getList(nbt, "Items");
		int randomNumber = random.nextIntBetweenInclusive(0, 7);

		switch (randomNumber) {
			case 0 -> addBrewingRecipe(itemsListNbt, "minecraft:glistering_melon_slice", "minecraft:healing", random);
			case 1 -> addBrewingRecipe(itemsListNbt, "minecraft:magma_cream", "minecraft:fire_resistance", random);
			case 2 -> addBrewingRecipe(itemsListNbt, "minecraft:sugar", "minecraft:swiftness", random);
			case 3 -> addBrewingRecipe(itemsListNbt, "minecraft:pufferfish", "minecraft:water_breathing", random);
			case 4 -> addBrewingRecipe(itemsListNbt, "minecraft:fermented_spider_eye", "minecraft:slowness", random);
			case 5 -> addBrewingRecipe(itemsListNbt, "minecraft:spider_eye", "minecraft:poison", random);
			case 6 -> addBrewingRecipe(itemsListNbt, "minecraft:fermented_spider_eye", "minecraft:weakness", random);
			case 7 -> addBrewingRecipe(itemsListNbt, "minecraft:fermented_spider_eye", "minecraft:harming", random);
		}

		return new StructureTemplate.StructureBlockInfo(currentBlockInfo.pos(), currentBlockInfo.state(), nbt);
	}

	private void addBrewingRecipe(
		ListTag itemsListTag,
		String inputItemId,
		String outputPotionId,
		RandomSource randomSource
	) {
		itemsListTag.add(Util.make(new CompoundTag(), itemTag -> {
			putInputItem(itemTag, inputItemId, (byte) (randomSource.nextInt(3) + 2));
		}));

		itemsListTag.add(Util.make(new CompoundTag(), itemTag -> {
			putPotionInSlot(itemTag, (byte) 1, outputPotionId);

			if (randomSource.nextFloat() < .75F) {
				putPotionInSlot(itemTag, (byte) 0, outputPotionId);
			}

			if (randomSource.nextFloat() < .5F) {
				putPotionInSlot(itemTag, (byte) 2, outputPotionId);
			}
		}));
	}

	private void putInputItem(CompoundTag itemTag, String itemId, byte count) {
		itemTag.putByte("Slot", (byte) 3);
		itemTag.putString("id", itemId);
		itemTag.putByte("Count", count);
	}

	private void putPotionInSlot(CompoundTag itemTag, byte slot, String potionId) {
		itemTag.putByte("Slot", slot);
		itemTag.putString("id", "minecraft:potion");
		itemTag.putByte("Count", (byte) 1);
		itemTag.put("tag", Util.make(new CompoundTag(), potionTag -> {
			potionTag.putString("Potion", potionId);
		}));
	}

	@Override
	//? if >= 26.2 {
	public MapCodec<VillageWitchBrewingStandProcessor> codec() {
		return CODEC;
	}
	//?} else {
	/*protected StructureProcessorType<?> getType() {
		return VillagesAndPillagesProcessorTypes.VILLAGE_WITCH_BREWING_STAND_PROCESSOR;
	}
	*///?}
}
