import printScreen.lexer.Lexer
import printScreen.parser.Parser

fun main() {
    val lexer = Lexer("println(1+2);")
    val tokens = lexer.tokenize()
    val parser = Parser()
    val ast = parser.parse(tokens)
    val linter = Linter()
    val violations = linter.lint(ast)
    print(violations)
}