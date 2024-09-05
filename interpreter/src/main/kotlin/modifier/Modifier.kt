package modifier

internal sealed interface Modifier {
  val type: String
  val value: Any?
}
