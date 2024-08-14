package printScreen.parser.genTree

import node.ASTNode
import token.Token

interface Builder {

    fun createAST() : ASTNode
}