package printScreen.parser.genTree

import node.ASTNode

interface Builder {

    fun build() : ASTNode
}