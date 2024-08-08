package printScreen

import printScreen.interpreter.Interpreter
import printScreen.lexer.Lexer
import printScreen.models.ast.AST
import printScreen.parser.Parser
import printScreen.parser.genTree.genTree
import printScreen.parser.verify.verifyAST
import printScreen.models.token.Token
import java.io.File

class runner (private val file : File) {

    private val lexer : Lexer = Lexer()
    private val parser : Parser = Parser(genTree() , verifyAST() )
    private val interpreter : Interpreter = Interpreter()

    fun run ( file : File ) {
        val tokens : List<Token>? = lexer.lex(file)
        val asts  : List<AST> =  parser.parse(tokens)
        interpreter.evaluat(asts)
    }
}