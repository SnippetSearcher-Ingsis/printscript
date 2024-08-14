import printScreen.lexer.Lexer
import printScreen.parser.Parser

fun main() {
    val lexer = Lexer("let x: number = 9 + 8;")
    val tokenizer = lexer.tokenize()
    val parser = Parser()
    println(parser.parse(tokenizer))
}