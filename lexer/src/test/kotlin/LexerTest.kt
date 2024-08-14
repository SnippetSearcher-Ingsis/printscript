


import org.junit.jupiter.api.Test
import printScreen.lexer.Lexer

class LexerTest {


    @Test
    fun simpleLexerTest() {
        val result = Lexer("let a : string = \"Hello World\";").tokenize()
        result.forEach { token -> println(token) }
    }
}