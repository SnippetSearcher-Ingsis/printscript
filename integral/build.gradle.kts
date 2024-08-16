plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_22
    targetCompatibility = JavaVersion.VERSION_22
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":interpreter"))
    implementation(project(":lexer"))
    implementation(project(":parser"))
}
