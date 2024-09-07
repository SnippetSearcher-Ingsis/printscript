package com.printscript.parser

import org.junit.jupiter.api.Test

class PrintParserTest {
  @Test
  fun testDeclaration() {
    val parser = PrintParser()
    val tokens = listOf(
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.LET, "let", 1, 1),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.IDENTIFIER, "a", 1, 5),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.SYNTAX, ":", 1, 7),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.TYPE, "string", 1, 9),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.EQUAL, "=", 1, 16),
      com.printscript.models.token.ValuedToken(
        com.printscript.models.token.TokenType.LITERAL,
        "\"Hello World\"",
        1,
        18
      ),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.SEMICOLON, ";", 1, 31)
    )
    val ast = listOf(
      com.printscript.models.node.VariableDeclarationNode(
        variable = "a",
        variableType = "string",
        expression = com.printscript.models.node.LiteralNode("\"Hello World\""),
        position = com.printscript.models.node.Position(1, 1)
      )
    )
    assert(parser.parse(listOf(tokens).iterator()).next() == ast.first())
  }

  @Test
  fun testAssignation() {
    val parser = PrintParser()
    val tokens = listOf(
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.LET, "let", 1, 1),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.IDENTIFIER, "a", 1, 5),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.SYNTAX, ":", 1, 7),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.TYPE, "string", 1, 9),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.EQUAL, "=", 1, 16),
      com.printscript.models.token.ValuedToken(
        com.printscript.models.token.TokenType.LITERAL,
        "\"Hello World\"",
        1,
        18
      ),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.SEMICOLON, ";", 1, 31),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.IDENTIFIER, "a", 1, 5),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.EQUAL, "=", 1, 16),
      com.printscript.models.token.ValuedToken(
        com.printscript.models.token.TokenType.LITERAL,
        "\"Hello Universe\"",
        1,
        18
      ),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.SEMICOLON, ";", 1, 31)
    )
    val ast = listOf(
      com.printscript.models.node.VariableDeclarationNode(
        variable = "a",
        variableType = "string",
        expression = com.printscript.models.node.LiteralNode("\"Hello World\""),
        position = com.printscript.models.node.Position(1, 1)
      ),
      com.printscript.models.node.AssignationNode(
        variable = "a",
        expression = com.printscript.models.node.LiteralNode("\"Hello Universe\""),
        position = com.printscript.models.node.Position(1, 1)
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
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.LET, "let", 1, 1),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.IDENTIFIER, "a", 1, 5),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.SYNTAX, ":", 1, 7),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.TYPE, "number", 1, 9),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.EQUAL, "=", 1, 16),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.LITERAL, "1", 1, 1),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.SYNTAX, "+", 1, 1),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.LITERAL, "2", 1, 1),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.SEMICOLON, ";", 1, 1)
    )
    val ast = listOf(
      com.printscript.models.node.VariableDeclarationNode(
        variable = "a",
        variableType = "number",
        expression = com.printscript.models.node.DoubleExpressionNode(
          left = com.printscript.models.node.LiteralNode(1),
          right = com.printscript.models.node.LiteralNode(2),
          operator = "+",
        ),
        position = com.printscript.models.node.Position(0, 0)
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
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.LET, "let", 1, 1),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.IDENTIFIER, "a", 1, 5),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.SYNTAX, ":", 1, 7),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.TYPE, "number", 1, 9),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.EQUAL, "=", 1, 16),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.LITERAL, "1", 1, 1),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.SYNTAX, "-", 1, 1),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.LITERAL, "2", 1, 1),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.SEMICOLON, ";", 1, 1)
    )
    val ast = listOf(
      com.printscript.models.node.VariableDeclarationNode(
        variable = "a",
        variableType = "number",
        expression = com.printscript.models.node.DoubleExpressionNode(
          left = com.printscript.models.node.LiteralNode(1),
          right = com.printscript.models.node.LiteralNode(2),
          operator = "-",
        ),
        position = com.printscript.models.node.Position(0, 0)
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
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.LET, "let", 1, 1),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.IDENTIFIER, "a", 1, 5),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.SYNTAX, ":", 1, 7),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.TYPE, "number", 1, 9),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.EQUAL, "=", 1, 16),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.LITERAL, "1", 1, 1),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.SYNTAX, "*", 1, 1),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.LITERAL, "2", 1, 1),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.SEMICOLON, ";", 1, 1)
    )
    val ast = listOf(
      com.printscript.models.node.VariableDeclarationNode(
        variable = "a",
        variableType = "number",
        expression = com.printscript.models.node.DoubleExpressionNode(
          left = com.printscript.models.node.LiteralNode(1),
          right = com.printscript.models.node.LiteralNode(2),
          operator = "*",
        ),
        position = com.printscript.models.node.Position(0, 0)
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
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.LET, "let", 1, 1),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.IDENTIFIER, "a", 1, 5),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.SYNTAX, ":", 1, 7),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.TYPE, "number", 1, 9),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.EQUAL, "=", 1, 16),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.LITERAL, "1", 1, 1),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.SYNTAX, "/", 1, 1),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.LITERAL, "2", 1, 1),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.SEMICOLON, ";", 1, 1)
    )
    val ast = listOf(
      com.printscript.models.node.VariableDeclarationNode(
        variable = "a",
        variableType = "number",
        expression = com.printscript.models.node.DoubleExpressionNode(
          left = com.printscript.models.node.LiteralNode(1),
          right = com.printscript.models.node.LiteralNode(2),
          operator = "/",
        ),
        position = com.printscript.models.node.Position(0, 0)
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
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.LET, "let", 1, 1),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.IDENTIFIER, "a", 1, 5),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.SYNTAX, ":", 1, 7),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.TYPE, "string", 1, 9),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.EQUAL, "=", 1, 16),
      com.printscript.models.token.ValuedToken(
        com.printscript.models.token.TokenType.LITERAL,
        "\"Hello World\"",
        1,
        18
      ),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.OPERATOR, "+", 1, 1),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.LITERAL, "2", 1, 1),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.SEMICOLON, ";", 1, 1)
    )
    val ast = listOf(
      com.printscript.models.node.VariableDeclarationNode(
        variable = "a",
        variableType = "string",
        expression = com.printscript.models.node.DoubleExpressionNode(
          left = com.printscript.models.node.LiteralNode("\"Hello World\""),
          right = com.printscript.models.node.LiteralNode(2),
          operator = "+",
        ),
        position = com.printscript.models.node.Position(0, 0)
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
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.LET, "let", 1, 1),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.IDENTIFIER, "a", 1, 5),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.SYNTAX, ":", 1, 7),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.TYPE, "string", 1, 1),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.EQUAL, "=", 1, 16),
      com.printscript.models.token.ValuedToken(
        com.printscript.models.token.TokenType.LITERAL,
        "\"Hello World\"",
        1,
        18
      ),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.OPERATOR, "+", 1, 1),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.LITERAL, "2", 1, 1),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.SEMICOLON, ";", 1, 1)
    )
    val ast = listOf(
      com.printscript.models.node.VariableDeclarationNode(
        variable = "a",
        variableType = "string",
        expression = com.printscript.models.node.DoubleExpressionNode(
          left = com.printscript.models.node.LiteralNode("\"Hello World\""),
          right = com.printscript.models.node.LiteralNode(2),
          operator = "+",
        ),
        position = com.printscript.models.node.Position(0, 0)
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
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.PRINTLN, "println", 1, 1),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.SYNTAX, "(", 1, 1),
      com.printscript.models.token.ValuedToken(
        com.printscript.models.token.TokenType.LITERAL,
        "\"Hello World\"",
        1,
        1
      ),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.SYNTAX, ")", 1, 1),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.SEMICOLON, ";", 1, 1)
    )
    val ast = listOf(
      com.printscript.models.node.PrintStatementNode(
        expression = com.printscript.models.node.LiteralNode("\"Hello World\""),
        position = com.printscript.models.node.Position(0, 0)
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
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.LET, "let", 1, 1),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.IDENTIFIER, "a", 1, 5),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.SYNTAX, ":", 1, 7),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.TYPE, "string", 1, 9),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.EQUAL, "=", 1, 16),
      com.printscript.models.token.ValuedToken(
        com.printscript.models.token.TokenType.LITERAL,
        "\"Hello World\"",
        1,
        18
      ),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.SEMICOLON, ";", 1, 1),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.PRINTLN, "println", 1, 1),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.SYNTAX, "(", 1, 1),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.IDENTIFIER, "a", 1, 1),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.SYNTAX, ")", 1, 1),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.SEMICOLON, ";", 1, 1)
    )
    val ast = listOf(
      com.printscript.models.node.VariableDeclarationNode(
        variable = "a",
        variableType = "string",
        expression = com.printscript.models.node.LiteralNode("\"Hello World\""),
        position = com.printscript.models.node.Position(0, 0)
      ),
      com.printscript.models.node.PrintStatementNode(
        expression = com.printscript.models.node.LiteralNode("a"),
        position = com.printscript.models.node.Position(0, 0)
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
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.LET, "let", 1, 1),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.IDENTIFIER, "a", 1, 5),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.SYNTAX, ":", 1, 7),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.TYPE, "number", 1, 9),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.EQUAL, "=", 1, 16),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.LITERAL, "1", 1, 1),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.OPERATOR, "+", 1, 1),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.LITERAL, "2", 1, 1),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.SEMICOLON, ";", 1, 1),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.PRINTLN, "println", 1, 1),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.SYNTAX, "(", 1, 1),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.IDENTIFIER, "a", 1, 1),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.OPERATOR, "-", 1, 1),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.LITERAL, "2", 1, 1),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.OPERATOR, "/", 1, 1),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.LITERAL, "2", 1, 1),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.SYNTAX, ")", 1, 1),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.SEMICOLON, ";", 1, 1)
    )
    val ast = listOf(
      com.printscript.models.node.VariableDeclarationNode(
        variable = "a",
        variableType = "number",
        expression = com.printscript.models.node.DoubleExpressionNode(
          left = com.printscript.models.node.LiteralNode(1),
          right = com.printscript.models.node.LiteralNode(2),
          operator = "+",
        ),
        position = com.printscript.models.node.Position(0, 0)
      ),
      com.printscript.models.node.PrintStatementNode(
        expression = com.printscript.models.node.DoubleExpressionNode(
          left = com.printscript.models.node.LiteralNode("a"),
          right = com.printscript.models.node.DoubleExpressionNode(
            left = com.printscript.models.node.LiteralNode(2),
            right = com.printscript.models.node.LiteralNode(2),
            operator = "/",
          ),
          operator = "-",
        ),
        position = com.printscript.models.node.Position(0, 0)
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
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.LET, "let", 1, 1),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.LET, "let", 1, 1),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.LET, "let", 1, 1),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.LET, "let", 1, 1),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.LET, "let", 1, 1),
    )
    val result: CatchableParser.CatchableParserIterator = parser.parse(listOf(tokens).iterator())
    while (result.hasNext()) {
      result.next()
    }
    assert(result.hasException())
    assert(result.getException() is IllegalArgumentException)
  }
}
