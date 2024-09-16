package com.printscript.interpreter.util

import java.io.File

class Loader(private val name: String) {
  fun loadOutput(): List<String> {
    val file = File("src/test/resources/scripts/$name.txt")
    val list = mutableListOf<String>()
    file.forEachLine { list.add(it) }
    return list.toList()
  }
}
