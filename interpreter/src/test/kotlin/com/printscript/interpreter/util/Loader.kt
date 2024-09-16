package com.printscript.interpreter.util

class Loader(private val name: String) {
  fun loadOutput(): List<String> {
    val resource = this::class.java.getResource("/scripts/$name.txt")
    return resource?.readText()?.replace("\n", "")?.split("\r") ?: throw IllegalArgumentException("Output file not found")
  }
}
