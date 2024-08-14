


import org.junit.jupiter.api.Test
import printScreen.lexer.Lexer

class LexerTest {
    @Test
    fun simpleLexerTest() {
        val result = Lexer("let a : string = \"Hello World\";").tokenize()
        result.forEach { token -> println(token) }
    }

    @Test
    fun complexLexerTest() {
        val result = Lexer("let a : string = \"Hello World\"; let b : string = \"Bye World\"; a = a + b;").tokenize()
        result.forEach { token -> println(token) }
    }

    @Test
    fun printLexerTest() {
        val result = Lexer("println(\"hola\")").tokenize()
        result.forEach { token -> println(token) }
    }
}