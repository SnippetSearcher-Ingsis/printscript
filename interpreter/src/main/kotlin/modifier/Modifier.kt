package modifier

internal sealed interface Modifier {
  fun getValue(): Any?

  fun getType(): String
}
