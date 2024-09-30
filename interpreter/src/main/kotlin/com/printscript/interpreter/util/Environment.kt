package com.printscript.interpreter.util

private const val PI = 3.1415

private const val GRAVITY = 9.81

internal object Environment {
  private val globalVariables = mapOf(
    "gravity" to GRAVITY,
    "pi" to PI,
    "BEST_FOOTBALL_CLUB" to "San Lorenzo"
  )

  fun getGlobalVariable(name: String): Any? {
    return globalVariables[name]
  }

  fun hasGlobalVariable(name: String): Boolean {
    return globalVariables.containsKey(name)
  }
}
