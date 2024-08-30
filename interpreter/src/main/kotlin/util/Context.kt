package util

internal class Context {
  val context = mutableMapOf<String, Any>()

  fun addOrUpdate(key: String, value: Any) {
    context[key] = value
  }

  infix fun get(key: String): Any? {
    return context[key]
  }

  infix fun has(key: String): Boolean {
    return context.containsKey(key)
  }

  fun clear() {
    context.clear()
  }

  fun clone(): Context {
    val newContext = Context()
    newContext.context.putAll(context)
    return newContext
  }

  fun merge(newContext: Context) {
    newContext.context.forEach { (key, value) ->
      if (has(key)) {
        addOrUpdate(key, value)
      }
    }
  }
}
