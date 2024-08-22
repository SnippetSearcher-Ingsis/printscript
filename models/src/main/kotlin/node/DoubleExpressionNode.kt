package node

class DoubleExpressionNode(val operator: String, val left: ASTNode, val right: ASTNode) : ASTNode {
  override fun accept(visitor: ASTVisitor) {
    visitor.visit(this)
  }

  override fun toString(): String {
    return "DoubleExpressionNode(operator='$operator', left=$left, right=$right)"
  }
}
