package com.printscript.interpreter.util

import com.printscript.interpreter.AssignationException
import com.printscript.interpreter.modifier.Constant
import com.printscript.interpreter.modifier.Modifier
import com.printscript.interpreter.modifier.Variable
import kotlin.jvm.Throws

internal class Context {
  private val context = mutableMapOf<String, Modifier>()

  @Throws(Exception::class)
  fun put(key: String, modifier: Modifier) {
    when {
      !(this has key) -> context[key] = modifier
      context[key] is Variable -> handleVariable(key, modifier.type, modifier.value)
      context[key] is Constant -> handleConstant(key, modifier.type, modifier.value)
    }
  }

  infix fun get(key: String): Modifier? {
    return context[key]
  }

  infix fun has(key: String): Boolean {
    return context.containsKey(key)
  }

  fun clone(): Context {
    val context = Context()
    context.context.putAll(this.context)
    return context
  }

  infix fun merge(context: Context) {
    context.context.forEach { (key, value) ->
      if (has(key) && get(key) is Variable) {
        put(key, value)
      }
    }
  }

  fun clear() {
    context.clear()
  }

  private fun handleConstant(key: String, type: String, value: Any?) {
    when {
      context[key]?.value != null -> throw AssignationException("Cannot reassign $key.")
      context[key]?.type != type -> throw AssignationException("Type mismatch. Cannot assign $type to $key.")
      else -> context[key] = Constant(type, value)
    }
  }

  private fun handleVariable(key: String, type: String, value: Any?) {
    when {
      context[key]?.type != type -> throw AssignationException("Type mismatch. Cannot assign $type to $key.")
      else -> context[key] = Variable(type, value)
    }
  }
}
