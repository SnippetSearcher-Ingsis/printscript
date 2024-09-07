package com.printscript.models.node

class IfElseNode(val ifBranch: List<ASTNode>, val elseBranch: List<ASTNode>, val condition: LiteralNode<*>) : ASTNode
