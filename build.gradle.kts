plugins {
    application
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.shadow)
    alias(libs.plugins.versions)
}

group = "dev.tabansi"
version = "0.1.0"

repositories {
    mavenCentral()
}

dependencies {
    // Kotlin stdlib matching the plugin version
    implementation(kotlin("stdlib"))

    // Testing
    testImplementation(kotlin("test"))
    testImplementation(kotlin("test-junit5"))
    testImplementation(libs.junit.jupiter)
}

application {
    mainClass.set("dev.tabansi.chess.MainKt")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(25))
    }
}

kotlin {
    // Build for broad compatibility so recruiters can run on Java 25
    jvmToolchain(25)
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    compilerOptions.jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_25)
}

tasks.test {
    useJUnitPlatform()
}

tasks.shadowJar {
    archiveBaseName.set("terminalchess")
    archiveClassifier.set("all")
    archiveVersion.set("")
}

tasks.jar {
    manifest {
        attributes(
            mapOf(
                "Main-Class" to application.mainClass.get()
            )
        )
    }
}
