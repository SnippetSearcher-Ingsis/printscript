import printScreen.TXTHandler
import printScreen.lexer.Lexer

fun main() {
    val si = Lexer(TXTHandler.content("/si.txt"))
    println(si.tokenize())
}