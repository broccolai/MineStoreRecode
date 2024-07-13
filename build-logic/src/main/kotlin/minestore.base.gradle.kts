plugins {
    id("java")
    id("java-library")
    id("net.kyori.indra")
}

group = rootProject.group
version = rootProject.version

indra {
    javaVersions {
        minimumToolchain(8)
        target(8)
    }
}

repositories {
    mavenCentral()
    sonatype.s01Snapshots()
    sonatype.ossSnapshots()
    maven("https://oss.sonatype.org/content/groups/public/")
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://jitpack.io/")
    maven("https://repo.extendedclip.com/content/repositories/placeholderapi/")
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.3"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks {
    withType(JavaCompile::class).configureEach {
        options.compilerArgs.add("-Xlint:all,-processing")
    }

    test {
        useJUnitPlatform()
    }
}