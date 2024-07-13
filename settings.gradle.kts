enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://jitpack.io")
        maven("https://maven.fabricmc.net/")
        mavenCentral()
    }

    resolutionStrategy {
        eachPlugin {
            if (requested.id.id == "me.chrommob.skidfuscatorgradle") {
                useModule("com.github.ChromMob.SkidFuscatorGradle:SkidFuscatorGradle:master-SNAPSHOT")
            }
        }
    }

    includeBuild("build-logic")
}

rootProject.name = "MineStore"

subproject("common")
subproject("bukkit")
subproject("velocity")
subproject("bungee")
 //subproject("sponge")
subproject("fabric")
subproject("addon-api", "AddonApi")

fun subproject(path: String, name: String = path) {
    val prefixedName = "MineStore-${name}"
    include(prefixedName)

    val project = project(":$prefixedName")
    project.projectDir = file(path)
}