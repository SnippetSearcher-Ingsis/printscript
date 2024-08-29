package node

data class DoubleExpressionNode(val operator: String, val left: ASTNode, val right: ASTNode) : ASTNode {
  override fun accept(visitor: ASTVisitor) {
    visitor.visit(this)
  }

  override fun toString(): String {
    return "DoubleExpressionNode(operator='$operator', left=$left, right=$right)"
  }

  override fun equals(other: Any?): Boolean {
    return when {
      other is DoubleExpressionNode -> {
        operator == other.operator && left == other.left && right == other.right
      }

      else -> super.equals(other)
    }
  }

  override fun hashCode(): Int {
    var result = operator.hashCode()
    result = 31 * result + left.hashCode()
    result = 31 * result + right.hashCode()
    return result
  }
}
