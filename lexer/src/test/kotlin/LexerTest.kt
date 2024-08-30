

import org.junit.jupiter.api.Test
import printScreen.lexer.Lexer
import java.io.File

class LexerTest {
  @Test
  fun simpleLexerTest() {
    val test = Lexer(File("src/test/resources/newLexer.txt").reader())
    while (test.hasNext()) {
      val tokens = test.next()
      println(tokens)
    }
  }
}
