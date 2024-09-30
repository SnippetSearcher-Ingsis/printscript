package com.printscript.models.node

data class IfElseNode(
    val ifBranch: List<ASTNode>,
    val elseBranch: List<ASTNode>,
    val condition: LiteralNode<*>
) : ASTNode
