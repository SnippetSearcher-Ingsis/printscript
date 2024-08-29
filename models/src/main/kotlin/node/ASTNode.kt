package node

sealed interface ASTNode {
  fun accept(visitor: ASTVisitor)
}
