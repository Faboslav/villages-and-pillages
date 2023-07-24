package com.faboslav.villagesandpillages.forge;

import com.faboslav.villagesandpillages.VillagesAndPillages;
import com.faboslav.villagesandpillages.platform.forge.StructureTypeRegistryImpl;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(VillagesAndPillages.MOD_ID)
@Mod.EventBusSubscriber(modid = VillagesAndPillages.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public final class VillagesAndPillagesForge
{
	public VillagesAndPillagesForge() {
		VillagesAndPillages.init();

		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

		StructureTypeRegistryImpl.STRUCTURE_TYPES.register(bus);

		bus.addListener(VillagesAndPillagesForge::init);

		MinecraftForge.EVENT_BUS.register(this);
	}

	private static void init(final FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			VillagesAndPillages.postInit();
		});
	}
}
