import printScreen.lexer.Lexer
import printScreen.parser.Parser

fun main() {
    val code = TXTHandler.content("test.txt")
    val lexer = Lexer(code)
    val tokenizer = lexer.tokenize()
    val parser = Parser()
    println(parser.parse(tokenizer))
}