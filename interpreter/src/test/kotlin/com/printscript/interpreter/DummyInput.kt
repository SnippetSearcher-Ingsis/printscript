package com.printscript.interpreter

import com.printscript.interpreter.input.Input

class DummyInput : Input {
  override fun read(message: String): String {
    return "Newell's Old Boys"
  }
}
