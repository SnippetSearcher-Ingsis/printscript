package token

sealed interface Token {
  val type: TokenType
  val value: String
  val column: Int
  val line: Int
}
