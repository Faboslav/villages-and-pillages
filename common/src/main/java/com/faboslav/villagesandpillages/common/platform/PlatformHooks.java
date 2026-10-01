package com.faboslav.villagesandpillages.common.platform;

import java.util.ServiceLoader;

public final class PlatformHooks
{
	public static final StructureProcessorTypes STRUCTURE_PROCESSOR_TYPES = load(StructureProcessorTypes.class);

	public static <T> T load(Class<T> service) {
		T loadedService = ServiceLoader.load(service)
			.findFirst()
			.orElseThrow(() -> new NullPointerException("No implementation found for " + service.getName()));
		return loadedService;
	}
}
