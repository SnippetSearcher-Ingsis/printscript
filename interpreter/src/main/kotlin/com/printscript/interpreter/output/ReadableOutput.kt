package com.printscript.interpreter.output

class ReadableOutput : Output {
  private val output = mutableListOf<String>()

  override fun write(message: String) {
    output.add(message)
  }

  fun getOutput(): List<String> = output
}
