package com.printscript.models.node

data class PrintStatementNode(val expression: ASTNode, val position: Position) :
  ASTNode {
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

  override fun toString(): String {
    return "println($expression)"
  }
}
