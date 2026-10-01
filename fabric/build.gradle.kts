val IS_CI = System.getenv("CI") == "true"

plugins {
	id("fabric-loom-compat")
	id("multiloader-loader")
	id("dev.kikugie.fletching-table.fabric") version "0.1.0-alpha.23"
}

fletchingTable {
	j52j.register("main") {
		extension("json", "**/*.json5")
	}
}

dependencies {
	minecraft("com.mojang:minecraft:${commonMod.mc}")

	if (stonecutter.eval(commonMod.mc, "<=1.21.11")) {
		mappings(loom.layered {
			officialMojangMappings()

			if (!IS_CI) {
				commonMod.depOrNull("parchment")?.let { parchmentVersion ->
					parchment("org.parchmentmc.data:parchment-${commonMod.mc}:$parchmentVersion@zip")
				}
			}
		})
	}

	modImplementation("net.fabricmc:fabric-loader:${commonMod.dep("fabric_loader")}")
	modApi("net.fabricmc.fabric-api:fabric-api:${commonMod.dep("fabric_api")}+${commonMod.mc}")
}

loom {
	runs {
		getByName("client") {
			client()
			ideConfigFolder.set("Fabric")
			configName = "Fabric Client"
			ideConfigGenerated(true)
		}
		getByName("server") {
			server()
			ideConfigFolder.set("Fabric")
			configName = "Fabric Server"
			ideConfigGenerated(true)
		}
	}

	if (stonecutter.eval(commonMod.mc, "<=1.21.11")) {
		mixin {
			useLegacyMixinAp = true
			defaultRefmapName = "${mod.id}.refmap.json"
		}
	}
}