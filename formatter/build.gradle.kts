plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "2.0.0"// Especifica la versión del plugin de Kotlin
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // add gson dependency
    implementation("com.google.code.gson:gson:2.10.1")
    implementation(project(":models"))
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8") // Cambiado a la dependencia completa de Kotlin
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain {
        (this as JavaToolchainSpec).languageVersion.set(JavaLanguageVersion.of(19)) // Especifica la versión de Java
    }
}
