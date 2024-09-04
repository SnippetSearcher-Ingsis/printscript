plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "2.0.0"// Especifica la versión del plugin de Kotlin
}

repositories {
    mavenCentral()
}

group = "org.example"
version = "1.0"

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.11.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8") // Cambiado a la dependencia completa de Kotlin
    implementation(project(":parser"))
    implementation(project(":lexer"))
    implementation(project(":models"))
    implementation(project(":interpreter"))
    implementation(project(":cli"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain {
        (this as JavaToolchainSpec).languageVersion.set(JavaLanguageVersion.of(19)) // Especifica la versión de Java
    }
}
