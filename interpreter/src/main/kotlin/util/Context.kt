package util

import AssignationException
import modifier.Modifier
import modifier.Variable
import kotlin.jvm.Throws

internal class Context {
  private val variables = mutableMapOf<String, Modifier>()

  @Throws(Exception::class)
  fun put(key: String, modifier: Modifier) {
    when {
      !(this has key) -> variables[key] = modifier
      variables[key] is Variable -> handleVariable(key, modifier as Variable)
    }
  }

  infix fun get(key: String): Modifier? {
    return variables[key]
  }

  infix fun has(key: String): Boolean {
    return variables.containsKey(key)
  }

  fun clear() {
    variables.clear()
  }

  private fun handleVariable(key: String, variable: Variable) {
    when {
      variables[key]?.getType() != variable.getType() -> throw AssignationException("Type mismatch. Cannot assign ${variable.getType()} to $key.")
      else -> variables[key] = variable
    }
  }
}
