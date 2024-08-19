plugins {
    id("java")
    kotlin("jvm") version "2.0.0"
    id("org.jlleitschuh.gradle.ktlint") version "11.0.0"
    id("com.diffplug.spotless") version "6.7.1"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
}

allprojects {
    apply(plugin = "kotlin")
    apply(plugin = "com.diffplug.spotless")
    dependencies {
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
        implementation("org.jetbrains.kotlin:kotlin-reflect")
    }
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain {
        this.languageVersion.set(JavaLanguageVersion.of(21))
    }
}

spotless {
    java {
        googleJavaFormat()
    }
}
