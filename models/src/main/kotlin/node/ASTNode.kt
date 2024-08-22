package node

interface ASTNode {
  fun accept(visitor: ASTVisitor)
}
