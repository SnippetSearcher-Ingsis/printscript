package util

import AssignationException
import modifier.Constant
import modifier.Modifier
import modifier.Variable
import kotlin.jvm.Throws

internal class Context {
  private val context = mutableMapOf<String, Modifier>()

  @Throws(Exception::class)
  fun put(key: String, modifier: Modifier) {
    when {
      !(this has key) -> context[key] = modifier
      context[key] is Variable -> handleVariable(key, Variable(modifier.type, modifier.value))
      context[key] is Constant -> handleConstant(key, Constant(modifier.type, modifier.value))
    }
  }

  infix fun get(key: String): Modifier? {
    return context[key]
  }

  infix fun has(key: String): Boolean {
    return context.containsKey(key)
  }

  fun clone(): Context {
    val newContext = Context()
    newContext.context.putAll(context)
    return newContext
  }

  infix fun merge(oldContext: Context) {
    oldContext.context.forEach { (key, value) ->
      if (has(key)) {
        put(key, value)
      }
    }
  }

  fun clear() {
    context.clear()
  }

  private fun handleConstant(key: String, constant: Constant) {
    when {
      context[key]?.value != null -> throw AssignationException("Cannot reassign $key.")
      context[key]?.type != constant.type -> throw AssignationException("Type mismatch. Cannot assign ${constant.type} to $key.")
      else -> context[key] = constant
    }
  }

  private fun handleVariable(key: String, variable: Variable) {
    when {
      context[key]?.type != variable.type -> throw AssignationException("Type mismatch. Cannot assign ${variable.type} to $key.")
      else -> context[key] = variable
    }
  }
}
