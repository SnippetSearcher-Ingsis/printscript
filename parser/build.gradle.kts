plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "2.0.0"// Especifica la versión del plugin de Kotlin
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.11.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8") // Cambiado a la dependencia completa de Kotlin
    api(project(":models"))
    testImplementation(project(":interpreter"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain {
        this.languageVersion.set(JavaLanguageVersion.of(19)) // Especifica la versión de Java
    }
}
