package com.printscript.interpreter.util

import com.printscript.interpreter.modifier.Modifier
import kotlin.jvm.Throws

class Context {
  private val context = mutableMapOf<String, Modifier>()

  @Throws(Exception::class)
  fun put(key: String, modifier: Modifier) {
    context[key] = modifier
  }

  infix fun get(key: String): Modifier? {
    return context[key]
  }

  operator fun contains(key: String): Boolean {
    return context.containsKey(key)
  }

  fun clone(): Context {
    val context = Context()
    context.context.putAll(this.context)
    return context
  }

  infix fun replace(context: Context) {
    context.context.forEach { (key, value) ->
      if (key in this.context) {
        this.put(key, value)
      }
    }
  }

  fun clear() {
    context.clear()
  }
}
