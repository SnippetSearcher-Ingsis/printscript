package node

class PrintStatementNode(val expression: ASTNode, val position: Position) : ASTNode {
  override fun accept(visitor: ASTVisitor) {
    visitor.visit(this)
  }

  override fun toString(): String {
    return "PrintStatementNode(expression=$expression)"
  }

  override fun equals(other: Any?): Boolean {
    return when {
      other is PrintStatementNode -> expression == other.expression
      else -> super.equals(other)
    }
  }

  override fun hashCode(): Int {
    var result = expression.hashCode()
    result = 31 * result + position.hashCode()
    return result
  }
}
