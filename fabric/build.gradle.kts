plugins {
	id("minestore.platform")
	id("fabric-loom") version "1.5-SNAPSHOT"
}

loom {
    splitEnvironmentSourceSets()
	mods {
		create("minestore") {
			sourceSet(sourceSets["main"])
			sourceSet(sourceSets["client"])
		}
	}
}

java {
	disableAutoTargetJvm()
}

dependencies {
	// To change the versions see the gradle.properties file
	minecraft("com.mojang:minecraft:${property("minecraft_version")}")
	mappings("net.fabricmc:yarn:${property("yarn_mappings")}:v2")
	modImplementation("net.fabricmc:fabric-loader:${property("loader_version")}")

	// Fabric API. This is technically optional, but you probably want it anyway.
	modImplementation("net.fabricmc.fabric-api:fabric-api:${property("fabric_version")}")
	modImplementation(include("net.kyori:adventure-platform-fabric:5.11.0-SNAPSHOT")!!)
	implementation("org.incendo:cloud-fabric:2.0.0-beta.7")

	implementation(projects.mineStoreCommon)
}
