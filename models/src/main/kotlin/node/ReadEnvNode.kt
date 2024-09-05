package node

class ReadEnvNode(val expression: ASTNode, val position: Position) : ASTNode {
  override fun accept(visitor: ASTVisitor) {
    visitor.visit(this)
  }

  override fun toString(): String {
    return "ReadEnvNode(expression=$expression)"
  }
}
