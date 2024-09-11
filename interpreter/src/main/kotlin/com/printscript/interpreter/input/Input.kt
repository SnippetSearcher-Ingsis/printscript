package com.printscript.interpreter.input

interface Input {
  infix fun read(message: String): String
}
