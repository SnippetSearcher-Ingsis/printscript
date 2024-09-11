package com.printscript.interpreter.input

class ConsoleInput : Input {
  override fun read(message: String): String {
    return readlnOrNull() ?: ""
  }
}
