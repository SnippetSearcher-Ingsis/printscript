package util

internal class Context : Iterable<Map.Entry<String, Any>> {
  private val variables = mutableMapOf<String, Any>()

  fun put(key: String, value: Any) {
    variables[key] = value
  }

  infix fun get(key: String): Any? {
    return variables[key]
  }

  infix fun has(key: String): Boolean {
    return variables.containsKey(key)
  }

  fun clear() {
    variables.clear()
  }

  override fun iterator(): Iterator<Map.Entry<String, Any>> {
    return variables.iterator()
  }
}
