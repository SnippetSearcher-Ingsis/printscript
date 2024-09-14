package com.printscript.interpreter.modifier

sealed interface Modifier {
  val type: String
  val value: Any?
}
