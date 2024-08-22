import node.AssignationNode
import node.DoubleExpressionNode
import node.LiteralNode
import node.Position
import node.PrintStatementNode
import node.VariableDeclarationNode
import org.junit.jupiter.api.Test
import printScreen.parser.CatchableParser
import printScreen.parser.Parser
import token.TokenType
import token.ValuedToken

class ParserTest {
  @Test
  fun testDeclaration() {
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
  fun testAssignation() {
    val parser = Parser()
    val tokens = listOf(
      ValuedToken(TokenType.LET, "let", 1, 1),
      ValuedToken(TokenType.IDENTIFIER, "a", 1, 5),
      ValuedToken(TokenType.SYNTAX, ":", 1, 7),
      ValuedToken(TokenType.TYPE, "string", 1, 9),
      ValuedToken(TokenType.EQUAL, "=", 1, 16),
      ValuedToken(TokenType.LITERAL, "\"Hello World\"", 1, 18),
      ValuedToken(TokenType.SEMICOLON, ";", 1, 31),
      ValuedToken(TokenType.IDENTIFIER, "a", 1, 5),
      ValuedToken(TokenType.EQUAL, "=", 1, 16),
      ValuedToken(TokenType.LITERAL, "\"Hello Universe\"", 1, 18),
      ValuedToken(TokenType.SEMICOLON, ";", 1, 31)
    )
    val ast = listOf(
      VariableDeclarationNode(
        variable = "a",
        variableType = "string",
        expression = LiteralNode("\"Hello World\""),
        position = Position(1, 1)
      ),
      AssignationNode(
        variable = "a",
        expression = LiteralNode("\"Hello Universe\""),
        position = Position(1, 1)
      )
    )
    assert(parser.parse(tokens).toString() == ast.toString())
  }

  @Test
  fun testAddition() {
    val parser = Parser()
    val tokens = listOf(
      ValuedToken(TokenType.LET, "let", 1, 1),
      ValuedToken(TokenType.IDENTIFIER, "a", 1, 5),
      ValuedToken(TokenType.SYNTAX, ":", 1, 7),
      ValuedToken(TokenType.TYPE, "number", 1, 9),
      ValuedToken(TokenType.EQUAL, "=", 1, 16),
      ValuedToken(TokenType.LITERAL, "1", 1, 1),
      ValuedToken(TokenType.SYNTAX, "+", 1, 1),
      ValuedToken(TokenType.LITERAL, "2", 1, 1),
      ValuedToken(TokenType.SEMICOLON, ";", 1, 1)
    )
    val ast = listOf(
      VariableDeclarationNode(
        variable = "a",
        variableType = "number",
        expression = DoubleExpressionNode(
          left = LiteralNode(1),
          right = LiteralNode(2),
          operator = "+",
        ),
        position = Position(0, 0)
      )
    )
    assert(parser.parse(tokens) == ast)
  }

  @Test
  fun testSubtraction() {
    val parser = Parser()
    val tokens = listOf(
      ValuedToken(TokenType.LET, "let", 1, 1),
      ValuedToken(TokenType.IDENTIFIER, "a", 1, 5),
      ValuedToken(TokenType.SYNTAX, ":", 1, 7),
      ValuedToken(TokenType.TYPE, "number", 1, 9),
      ValuedToken(TokenType.EQUAL, "=", 1, 16),
      ValuedToken(TokenType.LITERAL, "1", 1, 1),
      ValuedToken(TokenType.SYNTAX, "-", 1, 1),
      ValuedToken(TokenType.LITERAL, "2", 1, 1),
      ValuedToken(TokenType.SEMICOLON, ";", 1, 1)
    )
    val ast = listOf(
      VariableDeclarationNode(
        variable = "a",
        variableType = "number",
        expression = DoubleExpressionNode(
          left = LiteralNode(1),
          right = LiteralNode(2),
          operator = "-",
        ),
        position = Position(0, 0)
      )
    )
    assert(parser.parse(tokens) == ast)
  }

  @Test
  fun testMultiplication() {
    val parser = Parser()
    val tokens = listOf(
      ValuedToken(TokenType.LET, "let", 1, 1),
      ValuedToken(TokenType.IDENTIFIER, "a", 1, 5),
      ValuedToken(TokenType.SYNTAX, ":", 1, 7),
      ValuedToken(TokenType.TYPE, "number", 1, 9),
      ValuedToken(TokenType.EQUAL, "=", 1, 16),
      ValuedToken(TokenType.LITERAL, "1", 1, 1),
      ValuedToken(TokenType.SYNTAX, "*", 1, 1),
      ValuedToken(TokenType.LITERAL, "2", 1, 1),
      ValuedToken(TokenType.SEMICOLON, ";", 1, 1)
    )
    val ast = listOf(
      VariableDeclarationNode(
        variable = "a",
        variableType = "number",
        expression = DoubleExpressionNode(
          left = LiteralNode(1),
          right = LiteralNode(2),
          operator = "*",
        ),
        position = Position(0, 0)
      )
    )
    assert(parser.parse(tokens) == ast)
  }

  @Test
  fun testDivision() {
    val parser = Parser()
    val tokens = listOf(
      ValuedToken(TokenType.LET, "let", 1, 1),
      ValuedToken(TokenType.IDENTIFIER, "a", 1, 5),
      ValuedToken(TokenType.SYNTAX, ":", 1, 7),
      ValuedToken(TokenType.TYPE, "number", 1, 9),
      ValuedToken(TokenType.EQUAL, "=", 1, 16),
      ValuedToken(TokenType.LITERAL, "1", 1, 1),
      ValuedToken(TokenType.SYNTAX, "/", 1, 1),
      ValuedToken(TokenType.LITERAL, "2", 1, 1),
      ValuedToken(TokenType.SEMICOLON, ";", 1, 1)
    )
    val ast = listOf(
      VariableDeclarationNode(
        variable = "a",
        variableType = "number",
        expression = DoubleExpressionNode(
          left = LiteralNode(1),
          right = LiteralNode(2),
          operator = "/",
        ),
        position = Position(0, 0)
      )
    )
    assert(parser.parse(tokens) == ast)
  }

  @Test
  fun testStringAddition() {
    val parser = Parser()
    val tokens = listOf(
      ValuedToken(TokenType.LET, "let", 1, 1),
      ValuedToken(TokenType.IDENTIFIER, "a", 1, 5),
      ValuedToken(TokenType.SYNTAX, ":", 1, 7),
      ValuedToken(TokenType.TYPE, "string", 1, 9),
      ValuedToken(TokenType.EQUAL, "=", 1, 16),
      ValuedToken(TokenType.LITERAL, "\"Hello World\"", 1, 18),
      ValuedToken(TokenType.OPERATOR, "+", 1, 1),
      ValuedToken(TokenType.LITERAL, "2", 1, 1),
      ValuedToken(TokenType.SEMICOLON, ";", 1, 1)
    )
    val ast = listOf(
      VariableDeclarationNode(
        variable = "a",
        variableType = "string",
        expression = DoubleExpressionNode(
          left = LiteralNode("\"Hello World\""),
          right = LiteralNode(2),
          operator = "+",
        ),
        position = Position(0, 0)
      )
    )
    assert(parser.parse(tokens) == ast)
  }

  @Test
  fun testAdditionWithDifferentType() {
    val parser = Parser()
    val tokens = listOf(
      ValuedToken(TokenType.LET, "let", 1, 1),
      ValuedToken(TokenType.IDENTIFIER, "a", 1, 5),
      ValuedToken(TokenType.SYNTAX, ":", 1, 7),
      ValuedToken(TokenType.TYPE, "string", 1, 1),
      ValuedToken(TokenType.EQUAL, "=", 1, 16),
      ValuedToken(TokenType.LITERAL, "\"Hello World\"", 1, 18),
      ValuedToken(TokenType.OPERATOR, "+", 1, 1),
      ValuedToken(TokenType.LITERAL, "2", 1, 1),
      ValuedToken(TokenType.SEMICOLON, ";", 1, 1)
    )
    val ast = listOf(
      VariableDeclarationNode(
        variable = "a",
        variableType = "string",
        expression = DoubleExpressionNode(
          left = LiteralNode("\"Hello World\""),
          right = LiteralNode(2),
          operator = "+",
        ),
        position = Position(0, 0)
      )
    )
    assert(parser.parse(tokens) == ast)
  }

  @Test
  fun testStringPrint() {
    val parser = Parser()
    val tokens = listOf(
      ValuedToken(TokenType.PRINTLN, "println", 1, 1),
      ValuedToken(TokenType.SYNTAX, "(", 1, 1),
      ValuedToken(TokenType.LITERAL, "\"Hello World\"", 1, 1),
      ValuedToken(TokenType.SYNTAX, ")", 1, 1),
      ValuedToken(TokenType.SEMICOLON, ";", 1, 1)
    )
    val ast = listOf(
      PrintStatementNode(
        expression = LiteralNode("\"Hello World\""),
        position = Position(0, 0)
      )
    )
    assert(parser.parse(tokens) == ast)
  }

  @Test
  fun testVariablePrint() {
    val parser = Parser()
    val tokens = listOf(
      ValuedToken(TokenType.LET, "let", 1, 1),
      ValuedToken(TokenType.IDENTIFIER, "a", 1, 5),
      ValuedToken(TokenType.SYNTAX, ":", 1, 7),
      ValuedToken(TokenType.TYPE, "string", 1, 9),
      ValuedToken(TokenType.EQUAL, "=", 1, 16),
      ValuedToken(TokenType.LITERAL, "\"Hello World\"", 1, 18),
      ValuedToken(TokenType.SEMICOLON, ";", 1, 1),
      ValuedToken(TokenType.PRINTLN, "println", 1, 1),
      ValuedToken(TokenType.SYNTAX, "(", 1, 1),
      ValuedToken(TokenType.IDENTIFIER, "a", 1, 1),
      ValuedToken(TokenType.SYNTAX, ")", 1, 1),
      ValuedToken(TokenType.SEMICOLON, ";", 1, 1)
    )
    val ast = listOf(
      VariableDeclarationNode(
        variable = "a",
        variableType = "string",
        expression = LiteralNode("\"Hello World\""),
        position = Position(0, 0)
      ),
      PrintStatementNode(
        expression = LiteralNode("a"),
        position = Position(0, 0)
      )
    )
    assert(parser.parse(tokens) == ast)
  }

  @Test
  fun epicTest() {
    val parser = Parser()
    val tokens = listOf(
      ValuedToken(TokenType.LET, "let", 1, 1),
      ValuedToken(TokenType.IDENTIFIER, "a", 1, 5),
      ValuedToken(TokenType.SYNTAX, ":", 1, 7),
      ValuedToken(TokenType.TYPE, "number", 1, 9),
      ValuedToken(TokenType.EQUAL, "=", 1, 16),
      ValuedToken(TokenType.LITERAL, "1", 1, 1),
      ValuedToken(TokenType.OPERATOR, "+", 1, 1),
      ValuedToken(TokenType.LITERAL, "2", 1, 1),
      ValuedToken(TokenType.SEMICOLON, ";", 1, 1),
      ValuedToken(TokenType.PRINTLN, "println", 1, 1),
      ValuedToken(TokenType.SYNTAX, "(", 1, 1),
      ValuedToken(TokenType.IDENTIFIER, "a", 1, 1),
      ValuedToken(TokenType.OPERATOR, "-", 1, 1),
      ValuedToken(TokenType.LITERAL, "2", 1, 1),
      ValuedToken(TokenType.OPERATOR, "/", 1, 1),
      ValuedToken(TokenType.LITERAL, "2", 1, 1),
      ValuedToken(TokenType.SYNTAX, ")", 1, 1),
      ValuedToken(TokenType.SEMICOLON, ";", 1, 1)
    )
    val ast = listOf(
      VariableDeclarationNode(
        variable = "a",
        variableType = "number",
        expression = DoubleExpressionNode(
          left = LiteralNode(1),
          right = LiteralNode(2),
          operator = "+",
        ),
        position = Position(0, 0)
      ),
      PrintStatementNode(
        expression = DoubleExpressionNode(
          left = LiteralNode("a"),
          right = DoubleExpressionNode(
            left = LiteralNode(2),
            right = LiteralNode(2),
            operator = "/",
          ),
          operator = "-",
        ),
        position = Position(0, 0)
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
