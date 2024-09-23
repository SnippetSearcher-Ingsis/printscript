plugins {
    // Apply the java Plugin to add support for Java.
    id ("java")
    kotlin("jvm") version "2.0.0"

    // Our spotless convention file.
    id ("spotless-conventions")

    // Our kover convention file.
    id ("kover-conventions")

    // Our detekt convention file.
    id ("detekt-conventions")
}


