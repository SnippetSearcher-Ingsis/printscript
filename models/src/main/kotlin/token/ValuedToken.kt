package token

class ValuedToken(
    override val type: TokenType,
    override val value: String,
    override val column: Int,
    override val line: Int
) : Token
