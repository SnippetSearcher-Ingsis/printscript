package util

internal class Context : Iterable<Map.Entry<String, Any>> {
  private val variables = mutableMapOf<String, Any>()
  private val constants = mutableMapOf<String, Any>()

  fun put(key: String, value: Any) {
    variables[key] = value
  }

  fun putConstant(key: String, value: Any) {
    constants[key] = value
  }

  infix fun get(key: String): Any? {
    return if (variables.containsKey(key)) variables[key] else constants[key]
  }

  infix fun has(key: String): Boolean {
    return variables.containsKey(key) || isConstant(key)
  }

  infix fun isConstant(key: String): Boolean {
    return constants.containsKey(key)
  }

  fun clear() {
    variables.clear()
    constants.clear()
  }

  override fun iterator(): Iterator<Map.Entry<String, Any>> {
    return (variables + constants).iterator()
  }
}
// hola
