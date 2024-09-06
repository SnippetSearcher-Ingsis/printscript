package com.printscript.models.node

sealed interface ASTNode {
  fun accept(visitor: ASTVisitor)
}
