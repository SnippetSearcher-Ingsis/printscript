import printScreen.lexer.Lexer
import printScreen.parser.Parser

fun main() {
    val lexer = Lexer("let x:number = (8) + 9;")
    val tokenizer = lexer.tokenize()
    val parser = Parser()
    println(parser.parse(tokenizer))
}