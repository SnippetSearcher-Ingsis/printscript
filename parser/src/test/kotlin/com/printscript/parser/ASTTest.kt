package com.printscript.parser
import com.printscript.models.token.TokenType
import com.printscript.models.token.ValuedToken
import com.printscript.parser.generator.ASTGenerator
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ASTTest {

  @Test
  fun simpleStringGenTreeTest() {
    val example1 = listOf(
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
    )
    val result = ASTGenerator().createAST(example1)
    println(result)
  }

  @Test
  fun simpleNumberGenTreeTest() {
    val example2 = listOf(
      ValuedToken(TokenType.LET, "let", 1, 1),
      ValuedToken(TokenType.IDENTIFIER, "a", 1, 5),
      ValuedToken(TokenType.SYNTAX, ":", 1, 7),
      ValuedToken(TokenType.TYPE, "number", 1, 9),
      ValuedToken(TokenType.EQUAL, "=", 1, 16),
      ValuedToken(TokenType.LITERAL, "9", 1, 18),
      ValuedToken(TokenType.SEMICOLON, ";", 1, 31),
    )
    val result = ASTGenerator().createAST(example2)
    println(result)
  }

  @Test
  fun alreadyExistingVariableTest() {
    val example = listOf(
      ValuedToken(TokenType.IDENTIFIER, "a", 1, 1),
      ValuedToken(TokenType.EQUAL, "=", 1, 16),
      ValuedToken(TokenType.LITERAL, "9", 1, 18),
      ValuedToken(TokenType.SEMICOLON, ";", 1, 31),
    )
    val result = ASTGenerator().createAST(example)
    println(result)
  }

  @Test
  fun parenthesisTest() {
    val example = listOf(
      ValuedToken(TokenType.LET, "let", 1, 1),
      ValuedToken(TokenType.IDENTIFIER, "a", 1, 5),
      ValuedToken(TokenType.SYNTAX, ":", 1, 7),
      ValuedToken(TokenType.TYPE, "number", 1, 9),
      ValuedToken(TokenType.EQUAL, "=", 1, 16),
      ValuedToken(TokenType.SYNTAX, "(", 1, 16),
      ValuedToken(TokenType.LITERAL, "9", 1, 16),
      ValuedToken(TokenType.SYNTAX, "+", 1, 16),
      ValuedToken(TokenType.LITERAL, "8", 1, 16),
      ValuedToken(TokenType.SYNTAX, ")", 1, 16),
      ValuedToken(TokenType.SEMICOLON, ";", 1, 31),
    )

    val result = ASTGenerator().createAST(example)
    println(result)
  }

  @Test
  fun complexExpressionTest() {
    val example = listOf(
      ValuedToken(TokenType.LET, "let", 1, 1),
      ValuedToken(TokenType.IDENTIFIER, "a", 1, 5),
      ValuedToken(TokenType.SYNTAX, ":", 1, 7),
      ValuedToken(TokenType.TYPE, "number", 1, 9),
      ValuedToken(TokenType.EQUAL, "=", 1, 16),
      ValuedToken(TokenType.SYNTAX, "(", 1, 16),
      ValuedToken(TokenType.LITERAL, "9", 1, 16),
      ValuedToken(TokenType.SYNTAX, "+", 1, 16),
      ValuedToken(TokenType.LITERAL, "8", 1, 16),
      ValuedToken(TokenType.SYNTAX, ")", 1, 16),
      ValuedToken(TokenType.SYNTAX, "*", 1, 16),
      ValuedToken(TokenType.LITERAL, "8", 1, 16),
      ValuedToken(TokenType.SEMICOLON, ";", 1, 31),
    )

    val result = ASTGenerator().createAST(example)
    println(result)
  }

  @Test
  fun readEnv() {
    val example = listOf(
      ValuedToken(TokenType.LET, "let", 1, 1),
      ValuedToken(TokenType.IDENTIFIER, "a", 1, 5),
      ValuedToken(TokenType.SYNTAX, ":", 1, 7),
      ValuedToken(TokenType.TYPE, "number", 1, 9),
      ValuedToken(TokenType.EQUAL, "=", 1, 16),
      ValuedToken(TokenType.READ_ENV, "readEnv", 1, 16),
      ValuedToken(TokenType.SYNTAX, "(", 1, 16),
      ValuedToken(TokenType.LITERAL, "\"pi\"", 1, 16),
      ValuedToken(TokenType.SYNTAX, ")", 1, 16),
      ValuedToken(TokenType.SEMICOLON, ";", 1, 31),
    )

    val result = ASTGenerator().createAST(example)
    println(result)
  }

  @Test
  fun readInput() {
    val example = listOf(
      ValuedToken(TokenType.LET, "let", 1, 1),
      ValuedToken(TokenType.IDENTIFIER, "a", 1, 5),
      ValuedToken(TokenType.SYNTAX, ":", 1, 7),
      ValuedToken(TokenType.TYPE, "number", 1, 9),
      ValuedToken(TokenType.EQUAL, "=", 1, 16),
      ValuedToken(TokenType.READ_ENV, "readInput", 1, 16),
      ValuedToken(TokenType.SYNTAX, "(", 1, 16),
      ValuedToken(TokenType.LITERAL, "\"best football club = \"", 1, 16),
      ValuedToken(TokenType.SYNTAX, ")", 1, 16),
      ValuedToken(TokenType.SEMICOLON, ";", 1, 31),
    )

    val result = ASTGenerator().createAST(example)
    println(result)
  }

  @Test
  fun exceptionTest() {
    val example = listOf(
      ValuedToken(TokenType.LET, "let", 1, 1),
      ValuedToken(TokenType.IDENTIFIER, "a", 1, 5),
      ValuedToken(TokenType.SYNTAX, ":", 1, 7),
      ValuedToken(TokenType.TYPE, "number", 1, 9),
      ValuedToken(TokenType.EQUAL, "=", 1, 16),
      ValuedToken(
        TokenType.LITERAL,
        "\"Hello World\"",
        1,
        18
      ),
    )
    assertThrows<IllegalStateException> { ASTGenerator().createAST(example) }
  }
}
