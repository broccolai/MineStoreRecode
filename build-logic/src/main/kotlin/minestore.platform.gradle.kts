plugins {
    id("minestore.base")
    id("io.github.goooler.shadow")
}

tasks {
    val task = register("copyJar", Jar::class) {
        from(zipTree(shadowJar.flatMap { it.archiveFile }))
        archiveClassifier = "final"
        archiveFileName = "${project.name}-${project.version}.jar"
        destinationDirectory.set(rootProject.layout.buildDirectory.file("libs").get().asFile)
    }

    assemble {
        dependsOn(task)
    }

    shadowJar {
        relocate("org.incendo", "me.chrommob.minestore.libs.org.incendo")
        relocate("com.google.gson", "me.chrommob.minestore.libs.com.google.gson")
        relocate("com.zaxxer", "me.chrommob.minestore.libs.com.zaxxer")
        relocate("com.mysql", "me.chrommob.minestore.libs.com.mysql")
        relocate("org.mariadb", "me.chrommob.minestore.libs.org.mariadb")
        relocate("org.yaml", "me.chrommob.minestore.libs.org.yaml")
        relocate("net.kyori", "me.chrommob.minestore.libs.net.kyori") {
            exclude("net.kyori.adventure.platform.fabric.**")
        }
        exclude("META-INF/*.SF")
    }
}
