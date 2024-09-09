package com.printscript.interpreter

import com.printscript.interpreter.input.Input
import com.printscript.interpreter.tracer.Tracer

class DummyInput(private val tracer: Tracer) : Input {
  override fun read(message: String?): String {
    tracer.print(message ?: "")
    return "Newell's Old Boys"
  }
}
