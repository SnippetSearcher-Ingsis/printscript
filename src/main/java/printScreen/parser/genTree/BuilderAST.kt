package printScreen.parser.genTree

import printScreen.models.ast.AST
import printScreen.models.token.Token

interface BuilderAST {

    fun createAST (line : List<Token>) : AST
}