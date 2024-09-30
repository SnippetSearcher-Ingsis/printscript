plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "2.0.0"
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.11.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("com.google.code.gson:gson:2.8.9")
    implementation(project(":parser"))
    implementation(project(":lexer"))
    implementation(project(":models"))
    implementation(project(":formatter"))
    implementation(project(":linter"))
    implementation(project(":interpreter"))
    implementation(project(":cli"))
}

tasks.test {
    useJUnitPlatform()
}
