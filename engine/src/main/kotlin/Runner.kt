import printScreen.lexer.Lexer
import printScreen.parser.Parser

fun main() {
    val code = TXTHandler.content("test.txt")
    val lexer = Lexer(code)
    val tokens = lexer.tokenize()
    val parser = Parser()
    val ast = parser.parse(tokens)
    val interpreter = MyInterpreter()
    interpreter.interpret(ast)
}
