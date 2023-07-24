package com.faboslav.villagesandpillages.mixin;

import com.faboslav.villagesandpillages.VillagesAndPillages;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.ChunkRegion;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.feature.TreeFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.feature.util.FeatureContext;
import net.minecraft.world.gen.structure.Structure;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

;

@Mixin(TreeFeature.class)
public class NoTreesOnWitchVillageHouseMixin
{
	@Inject(
		method = "generate(Lnet/minecraft/world/gen/feature/util/FeatureContext;)Z",
		at = @At(value = "HEAD"),
		cancellable = true
	)
	private void villagesAndPillages_noTreesOnWitchVillageHouseMixin(
		FeatureContext<TreeFeatureConfig> context,
		CallbackInfoReturnable<Boolean> cir
	) {
		if (context.getWorld() instanceof ChunkRegion) {
			StructureWorldAccess world = context.getWorld();
			Registry<Structure> structureRegistry = world.getRegistryManager().getOptional(Registry.STRUCTURE_KEY).orElseThrow();
			StructureAccessor structureAccessor = ((ChunkRegionAccessor) world).getStructureAccessor();
			BlockPos originBlock = context.getOrigin();

			if (
				structureAccessor.getStructureAt(
					originBlock,
					structureRegistry.get(VillagesAndPillages.makeID("village_witch"))
				).hasChildren()
				&& world.getBlockState(originBlock.down()).isOf(Blocks.MOSS_BLOCK)
			) {
				cir.setReturnValue(false);
			}
		}
	}
}
