package util

internal class Context : Iterable<Map.Entry<String, Any>> {
  val context = mutableMapOf<String, Any>()

  fun put(key: String, value: Any) {
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

  override fun iterator(): Iterator<Map.Entry<String, Any>> = context.toMap().iterator()
}
// hola
