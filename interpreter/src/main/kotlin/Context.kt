object Context {
  private val context = mutableMapOf<String, Any>()

  fun add(key: String, value: Any) {
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
}
