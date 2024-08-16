import printScreen.lexer.Lexer
import printScreen.parser.Parser

fun main() {
    val lexer = Lexer(
        "let hola: number = 1 + 2; println(hola);"
    )
    val parser = Parser()
    val interpreter = MyInterpreter()
    val ast = parser.parse(lexer.tokenize())
    interpreter.interpret(ast)
}
