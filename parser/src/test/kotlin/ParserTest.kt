import node.LiteralNode
import node.Position
import node.VariableDeclarationNode
import org.junit.jupiter.api.Test
import printScreen.parser.CatchableParser
import printScreen.parser.Parser
import token.TokenType
import token.ValuedToken

class ParserTest {
  @Test
  fun testParser() {
    val parser = Parser()
    val tokens = listOf(
      ValuedToken(TokenType.LET, "let", 1, 1),
      ValuedToken(TokenType.IDENTIFIER, "a", 1, 5),
      ValuedToken(TokenType.SYNTAX, ":", 1, 7),
      ValuedToken(TokenType.TYPE, "string", 1, 9),
      ValuedToken(TokenType.EQUAL, "=", 1, 16),
      ValuedToken(TokenType.LITERAL, "\"Hello World\"", 1, 18),
      ValuedToken(TokenType.SEMICOLON, ";", 1, 31)
    )
    val ast = listOf(
      VariableDeclarationNode(
        variable = "a",
        variableType = "string",
        expression = LiteralNode("\"Hello World\""),
        position = Position(1, 1)
      )
    )
    assert(parser.parse(tokens) == ast)
  }

  @Test
  fun testCatchableParser() {
    val parser = CatchableParser()
    val tokens = listOf(
      ValuedToken(TokenType.LET, "let", 1, 1),
      ValuedToken(TokenType.LET, "let", 1, 1),
      ValuedToken(TokenType.LET, "let", 1, 1),
      ValuedToken(TokenType.LET, "let", 1, 1),
      ValuedToken(TokenType.LET, "let", 1, 1),
    )
    parser.parse(tokens)
    assert(parser.hasException())
    assert(parser.getException() is IllegalArgumentException)
  }
}
