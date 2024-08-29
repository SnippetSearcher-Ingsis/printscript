package token

data class ValuedToken(
  override val type: TokenType,
  override val value: String,
  override val line: Int,
  override val column: Int
) : Token {
  override fun toString(): String {
    return value
  }
}
