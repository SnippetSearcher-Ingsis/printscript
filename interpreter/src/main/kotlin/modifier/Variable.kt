package modifier

internal class Variable(private val type: String, private val value: Any?) : Modifier {
  override fun getValue(): Any? = value

  override fun getType(): String = type
}
