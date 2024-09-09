package com.printscript.interpreter.input

interface Input {
  fun read(message: String? = null): String
}
