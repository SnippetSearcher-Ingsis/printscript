package com.printscript.parser

import com.printscript.models.node.AssignationNode
import com.printscript.models.node.DoubleExpressionNode
import com.printscript.models.node.LiteralNode
import com.printscript.models.node.Position
import com.printscript.models.node.PrintStatementNode
import com.printscript.models.node.VariableDeclarationNode
import com.printscript.models.token.TokenType
import com.printscript.models.token.ValuedToken
import org.junit.jupiter.api.Test

class PrintParserTest {
  @Test
  fun testDeclaration() {
    val parser = PrintParser()
    val tokens = listOf(
      ValuedToken(TokenType.LET, "let", 1, 1),
      ValuedToken(TokenType.IDENTIFIER, "a", 1, 5),
      ValuedToken(TokenType.SYNTAX, ":", 1, 7),
      ValuedToken(TokenType.TYPE, "string", 1, 9),
      ValuedToken(TokenType.EQUAL, "=", 1, 16),
      ValuedToken(
        TokenType.LITERAL,
        "\"Hello World\"",
        1,
        18
      ),
      ValuedToken(TokenType.SEMICOLON, ";", 1, 31)
    )
    val ast = listOf(
      VariableDeclarationNode(
        identifier = "a",
        valueType = "string",
        expression = LiteralNode("\"Hello World\""),
        position = Position(1, 1)
      )
    )
    assert(parser.parse(listOf(tokens).iterator()).next() == ast.first())
  }

  @Test
  fun testAssignation() {
    val parser = PrintParser()
    val tokens = listOf(
      ValuedToken(TokenType.LET, "let", 1, 1),
      ValuedToken(TokenType.IDENTIFIER, "a", 1, 5),
      ValuedToken(TokenType.SYNTAX, ":", 1, 7),
      ValuedToken(TokenType.TYPE, "string", 1, 9),
      ValuedToken(TokenType.EQUAL, "=", 1, 16),
      ValuedToken(
        TokenType.LITERAL,
        "\"Hello World\"",
        1,
        18
      ),
      ValuedToken(TokenType.SEMICOLON, ";", 1, 31),
      ValuedToken(TokenType.IDENTIFIER, "a", 1, 5),
      ValuedToken(TokenType.EQUAL, "=", 1, 16),
      ValuedToken(
        TokenType.LITERAL,
        "\"Hello Universe\"",
        1,
        18
      ),
      ValuedToken(TokenType.SEMICOLON, ";", 1, 31)
    )
    val ast = listOf(
      VariableDeclarationNode(
        identifier = "a",
        valueType = "string",
        expression = LiteralNode("\"Hello World\""),
        position = Position(1, 1)
      ),
      AssignationNode(
        variable = "a",
        expression = LiteralNode("\"Hello Universe\""),
        position = Position(1, 1)
      )
    )
    val result = parser.parse(listOf(tokens).iterator())
    var i = 0
    while (result.hasNext()) {
      assert(result.next() == ast[i])
      i++
    }
  }

  @Test
  fun testAddition() {
    val parser = PrintParser()
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
        identifier = "a",
        valueType = "number",
        expression = DoubleExpressionNode(
          left = LiteralNode(1),
          right = LiteralNode(2),
          operator = "+",
        ),
        position = Position(0, 0)
      )
    )
    val result = parser.parse(listOf(tokens).iterator())
    var i = 0
    while (result.hasNext()) {
      assert(result.next() == ast[i])
      i++
    }
  }

  @Test
  fun testSubtraction() {
    val parser = PrintParser()
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
        identifier = "a",
        valueType = "number",
        expression = DoubleExpressionNode(
          left = LiteralNode(1),
          right = LiteralNode(2),
          operator = "-",
        ),
        position = Position(0, 0)
      )
    )
    val result = parser.parse(listOf(tokens).iterator())
    var i = 0
    while (result.hasNext()) {
      assert(result.next() == ast[i])
      i++
    }
  }

  @Test
  fun testMultiplication() {
    val parser = PrintParser()
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
        identifier = "a",
        valueType = "number",
        expression = DoubleExpressionNode(
          left = LiteralNode(1),
          right = LiteralNode(2),
          operator = "*",
        ),
        position = Position(0, 0)
      )
    )
    val result = parser.parse(listOf(tokens).iterator())
    var i = 0
    while (result.hasNext()) {
      assert(result.next() == ast[i])
      i++
    }
  }

  @Test
  fun testDivision() {
    val parser = PrintParser()
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
        identifier = "a",
        valueType = "number",
        expression = DoubleExpressionNode(
          left = LiteralNode(1),
          right = LiteralNode(2),
          operator = "/",
        ),
        position = Position(0, 0)
      )
    )
    val result = parser.parse(listOf(tokens).iterator())
    var i = 0
    while (result.hasNext()) {
      assert(result.next() == ast[i])
      i++
    }
  }

  @Test
  fun testStringAddition() {
    val parser = PrintParser()
    val tokens = listOf(
      ValuedToken(TokenType.LET, "let", 1, 1),
      ValuedToken(TokenType.IDENTIFIER, "a", 1, 5),
      ValuedToken(TokenType.SYNTAX, ":", 1, 7),
      ValuedToken(TokenType.TYPE, "string", 1, 9),
      ValuedToken(TokenType.EQUAL, "=", 1, 16),
      ValuedToken(
        TokenType.LITERAL,
        "\"Hello World\"",
        1,
        18
      ),
      ValuedToken(TokenType.OPERATOR, "+", 1, 1),
      ValuedToken(TokenType.LITERAL, "2", 1, 1),
      ValuedToken(TokenType.SEMICOLON, ";", 1, 1)
    )
    val ast = listOf(
      VariableDeclarationNode(
        identifier = "a",
        valueType = "string",
        expression = DoubleExpressionNode(
          left = LiteralNode("\"Hello World\""),
          right = LiteralNode(2),
          operator = "+",
        ),
        position = Position(0, 0)
      )
    )
    val result = parser.parse(listOf(tokens).iterator())
    var i = 0
    while (result.hasNext()) {
      assert(result.next() == ast[i])
      i++
    }
  }

  @Test
  fun testAdditionWithDifferentType() {
    val parser = PrintParser()
    val tokens = listOf(
      ValuedToken(TokenType.LET, "let", 1, 1),
      ValuedToken(TokenType.IDENTIFIER, "a", 1, 5),
      ValuedToken(TokenType.SYNTAX, ":", 1, 7),
      ValuedToken(TokenType.TYPE, "string", 1, 1),
      ValuedToken(TokenType.EQUAL, "=", 1, 16),
      ValuedToken(
        TokenType.LITERAL,
        "\"Hello World\"",
        1,
        18
      ),
      ValuedToken(TokenType.OPERATOR, "+", 1, 1),
      ValuedToken(TokenType.LITERAL, "2", 1, 1),
      ValuedToken(TokenType.SEMICOLON, ";", 1, 1)
    )
    val ast = listOf(
      VariableDeclarationNode(
        identifier = "a",
        valueType = "string",
        expression = DoubleExpressionNode(
          left = LiteralNode("\"Hello World\""),
          right = LiteralNode(2),
          operator = "+",
        ),
        position = Position(0, 0)
      )
    )
    val result = parser.parse(listOf(tokens).iterator())
    var i = 0
    while (result.hasNext()) {
      assert(result.next() == ast[i])
      i++
    }
  }

  @Test
  fun testStringPrint() {
    val parser = PrintParser()
    val tokens = listOf(
      ValuedToken(TokenType.PRINTLN, "println", 1, 1),
      ValuedToken(TokenType.SYNTAX, "(", 1, 1),
      ValuedToken(
        TokenType.LITERAL,
        "\"Hello World\"",
        1,
        1
      ),
      ValuedToken(TokenType.SYNTAX, ")", 1, 1),
      ValuedToken(TokenType.SEMICOLON, ";", 1, 1)
    )
    val ast = listOf(
      PrintStatementNode(
        expression = LiteralNode("\"Hello World\""),
        position = Position(0, 0)
      )
    )
    val result = parser.parse(listOf(tokens).iterator())
    var i = 0
    while (result.hasNext()) {
      assert(result.next() == ast[i])
      i++
    }
  }

  @Test
  fun testVariablePrint() {
    val parser = PrintParser()
    val tokens = listOf(
      ValuedToken(TokenType.LET, "let", 1, 1),
      ValuedToken(TokenType.IDENTIFIER, "a", 1, 5),
      ValuedToken(TokenType.SYNTAX, ":", 1, 7),
      ValuedToken(TokenType.TYPE, "string", 1, 9),
      ValuedToken(TokenType.EQUAL, "=", 1, 16),
      ValuedToken(
        TokenType.LITERAL,
        "\"Hello World\"",
        1,
        18
      ),
      ValuedToken(TokenType.SEMICOLON, ";", 1, 1),
      ValuedToken(TokenType.PRINTLN, "println", 1, 1),
      ValuedToken(TokenType.SYNTAX, "(", 1, 1),
      ValuedToken(TokenType.IDENTIFIER, "a", 1, 1),
      ValuedToken(TokenType.SYNTAX, ")", 1, 1),
      ValuedToken(TokenType.SEMICOLON, ";", 1, 1)
    )
    val ast = listOf(
      VariableDeclarationNode(
        identifier = "a",
        valueType = "string",
        expression = LiteralNode("\"Hello World\""),
        position = Position(0, 0)
      ),
      PrintStatementNode(
        expression = LiteralNode("a"),
        position = Position(0, 0)
      )
    )
    val result = parser.parse(listOf(tokens).iterator())
    var i = 0
    while (result.hasNext()) {
      assert(result.next() == ast[i])
      i++
    }
  }

  @Test
  fun epicTest() {
    val parser = PrintParser()
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
        identifier = "a",
        valueType = "number",
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
    val result = parser.parse(listOf(tokens).iterator())
    var i = 0
    while (result.hasNext()) {
      assert(result.next() == ast[i])
      i++
    }
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
    val result: CatchableParser.CatchableParserIterator = parser.parse(listOf(tokens).iterator())
    while (result.hasNext()) {
      result.next()
    }
    assert(result.hasException())
    assert(result.getException() is IllegalArgumentException)
  }
}
