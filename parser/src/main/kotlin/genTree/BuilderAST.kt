package printScreen.parser.genTree

import ast.AST
import token.Token

interface BuilderAST {

    fun createAST (line : List<Token>) : AST
}