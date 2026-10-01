plugins {
	`multiloader-loader`
	id("net.neoforged.moddev.legacyforge")
	id("dev.kikugie.fletching-table.neoforge") version "0.1.0-alpha.23"
}

mixin {
	add(sourceSets.main.get(), "${mod.id}.refmap.json")

	config("${mod.id}-common.mixins.json")
}

fletchingTable {
	j52j.register("main") {
		extension("json", "**/*.json5")
	}
}

legacyForge {
	enable {
		forgeVersion = "${commonMod.mc}-${commonMod.dep("forge")}"
	}
}

dependencies {
	compileOnly("org.jetbrains:annotations:24.1.0")
	annotationProcessor("org.spongepowered:mixin:0.8.5-SNAPSHOT:processor")

	"io.github.llamalad7:mixinextras-common:0.4.1".let {
		compileOnly(it)
		annotationProcessor(it)
	}

	"io.github.llamalad7:mixinextras-forge:0.4.1".let {
		implementation(it)
		jarJar(it)
	}
}

legacyForge {
	runs {
		register("client") {
			client()
			ideFolderName = "Forge"
			ideName = "Forge Client (${project.path})"
		}
		register("server") {
			server()
			ideFolderName = "Forge"
			ideName = "Forge Server (${project.path})"
		}
	}

	parchment {
		commonMod.depOrNull("parchment")?.let {
			mappingsVersion = it
			minecraftVersion = commonMod.mc
		}
	}

	mods {
		register(commonMod.id) {
			sourceSet(sourceSets.main.get())
		}
	}
}

sourceSets.main {
	resources.srcDir("src/generated/resources")
}

tasks {
	jar {
		finalizedBy("reobfJar")
		manifest {
			attributes(
				mapOf("MixinConfigs" to "${mod.id}-common.mixins.json")
			)
		}
	}
}

tasks.named("createMinecraftArtifacts") {
	dependsOn(":forge:${commonMod.propOrNull("minecraft_version")}:processResources")
}
