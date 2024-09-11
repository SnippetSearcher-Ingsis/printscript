package com.printscript.interpreter.util

internal object Environment {
  private val globalVariables = mapOf(
    "gravity" to 9.81,
    "pi" to 3.1415,
    "BEST_FOOTBALL_CLUB" to "San Lorenzo"
  )

  fun getGlobalVariable(name: String): Any? {
    return globalVariables[name]
  }

  fun hasGlobalVariable(name: String): Boolean {
    return globalVariables.containsKey(name)
  }
}
