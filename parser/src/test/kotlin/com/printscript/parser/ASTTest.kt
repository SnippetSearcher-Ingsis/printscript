package com.printscript.parser
import com.printscript.parser.generator.ASTGenerator
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ASTTest {

  @Test
  fun simpleStringGenTreeTest() {
    val example1 = listOf(
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
    )
    val result = ASTGenerator().tokensToAST(example1)
    println(result)
  }

  @Test
  fun simpleNumberGenTreeTest() {
    val example2 = listOf(
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.LET, "let", 1, 1),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.IDENTIFIER, "a", 1, 5),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.SYNTAX, ":", 1, 7),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.TYPE, "number", 1, 9),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.EQUAL, "=", 1, 16),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.LITERAL, "9", 1, 18),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.SEMICOLON, ";", 1, 31),
    )
    val result = ASTGenerator().tokensToAST(example2)
    println(result)
  }

  @Test
  fun alreadyExistingVariableTest() {
    val example = listOf(
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.IDENTIFIER, "a", 1, 1),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.EQUAL, "=", 1, 16),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.LITERAL, "9", 1, 18),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.SEMICOLON, ";", 1, 31),
    )
    val result = ASTGenerator().tokensToAST(example)
    println(result)
  }

  @Test
  fun parenthesisTest() {
    val example = listOf(
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.LET, "let", 1, 1),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.IDENTIFIER, "a", 1, 5),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.SYNTAX, ":", 1, 7),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.TYPE, "number", 1, 9),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.EQUAL, "=", 1, 16),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.SYNTAX, "(", 1, 16),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.LITERAL, "9", 1, 16),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.SYNTAX, "+", 1, 16),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.LITERAL, "8", 1, 16),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.SYNTAX, ")", 1, 16),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.SEMICOLON, ";", 1, 31),
    )

    val result = ASTGenerator().tokensToAST(example)
    println(result)
  }

  @Test
  fun complexExpressionTest() {
    val example = listOf(
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.LET, "let", 1, 1),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.IDENTIFIER, "a", 1, 5),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.SYNTAX, ":", 1, 7),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.TYPE, "number", 1, 9),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.EQUAL, "=", 1, 16),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.SYNTAX, "(", 1, 16),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.LITERAL, "9", 1, 16),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.SYNTAX, "+", 1, 16),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.LITERAL, "8", 1, 16),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.SYNTAX, ")", 1, 16),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.SYNTAX, "*", 1, 16),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.LITERAL, "8", 1, 16),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.SEMICOLON, ";", 1, 31),
    )

    val result = ASTGenerator().tokensToAST(example)
    println(result)
  }

  @Test
  fun exceptionTest() {
    val example = listOf(
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.LET, "let", 1, 1),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.IDENTIFIER, "a", 1, 5),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.SYNTAX, ":", 1, 7),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.TYPE, "number", 1, 9),
      com.printscript.models.token.ValuedToken(com.printscript.models.token.TokenType.EQUAL, "=", 1, 16),
      com.printscript.models.token.ValuedToken(
        com.printscript.models.token.TokenType.LITERAL,
        "\"Hello World\"",
        1,
        18
      ),
    )
    assertThrows<IllegalArgumentException> { ASTGenerator().tokensToAST(example) }
  }
}
