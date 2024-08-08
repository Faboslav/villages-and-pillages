package com.faboslav.villagesandpillages.world.processor;

import com.faboslav.villagesandpillages.init.VillagesAndPillagesProcessorTypes;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.Blocks;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.StructureTemplate;
import net.minecraft.structure.processor.StructureProcessor;
import net.minecraft.structure.processor.StructureProcessorType;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.WorldView;

public class VillageWitchBrewingStandProcessor extends StructureProcessor
{
	public static final MapCodec<VillageWitchBrewingStandProcessor> CODEC = MapCodec.unit(VillageWitchBrewingStandProcessor::new);

	@Override
	public StructureTemplate.StructureBlockInfo process(
		WorldView world,
		BlockPos pos,
		BlockPos pivot,
		StructureTemplate.StructureBlockInfo originalBlockInfo,
		StructureTemplate.StructureBlockInfo currentBlockInfo,
		StructurePlacementData structurePlacementData
	) {
		if (currentBlockInfo.state().isOf(Blocks.BREWING_STAND) == false) {
			return currentBlockInfo;
		}

		Random random = structurePlacementData.getRandom(currentBlockInfo.pos());
		NbtCompound nbt = currentBlockInfo.nbt();
		NbtList itemsListNbt = nbt.getList("Items", 10);

		int randomNumber = random.nextBetween(0, 7);

		switch (randomNumber) {
			case 0 -> addBrewingRecipe(
				itemsListNbt,
				"minecraft:glistering_melon_slice",
				"minecraft:healing",
				random
			);
			case 1 -> addBrewingRecipe(
				itemsListNbt,
				"minecraft:magma_cream",
				"minecraft:fire_resistance",
				random
			);
			case 2 -> addBrewingRecipe(
				itemsListNbt,
				"minecraft:sugar",
				"minecraft:swiftness",
				random
			);
			case 3 -> addBrewingRecipe(
				itemsListNbt,
				"minecraft:pufferfish",
				"minecraft:water_breathing",
				random
			);
			case 4 -> addBrewingRecipe(
				itemsListNbt,
				"minecraft:fermented_spider_eye",
				"minecraft:slowness",
				random
			);
			case 5 -> addBrewingRecipe(
				itemsListNbt,
				"minecraft:spider_eye",
				"minecraft:poison",
				random
			);
			case 6 -> addBrewingRecipe(
				itemsListNbt,
				"minecraft:fermented_spider_eye",
				"minecraft:weakness",
				random
			);
			case 7 -> addBrewingRecipe(
				itemsListNbt,
				"minecraft:fermented_spider_eye",
				"minecraft:harming",
				random
			);
		}

		currentBlockInfo = new StructureTemplate.StructureBlockInfo(currentBlockInfo.pos(), currentBlockInfo.state(), nbt);

		return currentBlockInfo;
	}

	private void addBrewingRecipe(
		NbtList itemsListTag,
		String inputItemId,
		String outputPotionId,
		Random randomSource
	) {
		itemsListTag.add(Util.make(new NbtCompound(), itemTag -> {
			putInputItem(itemTag, inputItemId, (byte) (randomSource.nextInt(3) + 2));
		}));

		itemsListTag.add(Util.make(new NbtCompound(), itemTag -> {
			putPotionInSlot(itemTag, (byte) 1, outputPotionId);
			if (randomSource.nextFloat() < .75f) putPotionInSlot(itemTag, (byte) 0, outputPotionId);
			if (randomSource.nextFloat() < .5f) putPotionInSlot(itemTag, (byte) 2, outputPotionId);
		}));
	}

	private void putInputItem(NbtCompound itemTag, String itemId, byte count) {
		itemTag.putByte("Slot", (byte) 3);
		itemTag.putString("id", itemId);
		itemTag.putByte("Count", count);
	}

	private void putPotionInSlot(NbtCompound itemTag, byte slot, String potionId) {
		itemTag.putByte("Slot", slot);
		itemTag.putString("id", "minecraft:potion");
		itemTag.putByte("Count", (byte) 1);
		itemTag.put("tag", Util.make(new NbtCompound(), potionTag -> {
			potionTag.putString("Potion", potionId);
		}));
	}

	@Override
	protected StructureProcessorType<?> getType() {
		return VillagesAndPillagesProcessorTypes.VILLAGE_WITCH_BREWING_STAND_PROCESSOR;
	}
}
