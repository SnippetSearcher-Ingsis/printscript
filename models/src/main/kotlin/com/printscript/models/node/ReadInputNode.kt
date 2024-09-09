package com.printscript.models.node

data class ReadInputNode(val expression: ASTNode, val position: Position) : ASTNode
