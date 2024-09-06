package com.printscript.lexer

import org.junit.jupiter.api.Test
import java.io.File

class LexerTest {
  @Test
  fun simpleLexerTest() {
    val lexer = Lexer()
    val iterator = lexer.lex(File("src/test/resources/test.txt").reader())
    while (iterator.hasNext()) {
      println(iterator.next())
    }
  }
}
