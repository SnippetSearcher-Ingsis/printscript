package com.printscript.interpreter.input

import com.printscript.interpreter.tracer.Tracer

class ConsoleInput(private val tracer: Tracer) : Input {
  override fun read(message: String?): String {
    tracer.print(message ?: "")
    return readlnOrNull() ?: ""
  }
}
