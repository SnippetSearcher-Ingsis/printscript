package com.printscript.lexer

import com.printscript.lexer.util.PreConfiguredTokens.TOKENS_1_1
import org.junit.jupiter.api.Test
import java.io.File

class LexerTest {
  @Test
  fun simpleLexerTest() {
    val lexer = Lexer(TOKENS_1_1)
    val iterator = lexer.lex(File("src/test/resources/test.txt").reader())
    while (iterator.hasNext()) {
      println(iterator.next())
    }
  }
}
