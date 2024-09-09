package com.printscript.interpreter.output

class ConsoleOutput : Output {
  override fun write(message: String) {
    println(message)
  }
}
