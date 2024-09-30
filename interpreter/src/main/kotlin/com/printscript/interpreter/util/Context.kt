package com.printscript.interpreter.util

import com.printscript.interpreter.modifier.Modifier
import kotlin.jvm.Throws

class Context {
  private val contextMap = mutableMapOf<String, Modifier>()

  @Throws(Exception::class)
  fun put(key: String, modifier: Modifier) {
    contextMap[key] = modifier
  }

  infix fun get(key: String): Modifier? {
    return contextMap[key]
  }

  operator fun contains(key: String): Boolean {
    return contextMap.containsKey(key)
  }

  fun clone(): Context {
    val context = Context()
    context.contextMap.putAll(this.contextMap)
    return context
  }

  infix fun replace(context: Context) {
    context.contextMap.forEach { (key, value) ->
      if (key in this.contextMap) {
        this.put(key, value)
      }
    }
  }

  fun clear() {
    contextMap.clear()
  }
}
