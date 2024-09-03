package node

data class VariableDeclarationNode(
  override val variable: String,
  override val variableType: String,
  override val expression: ASTNode,
  override val position: Position,
) : DeclarationNode {
  override fun accept(visitor: ASTVisitor) {
    visitor.visit(this)
  }

  override fun toString(): String {
    return "VariableDeclarationNode(variable=$variable, variableType=$variableType, expression=$expression)"
  }

  override fun equals(other: Any?): Boolean {
    return when {
      other is VariableDeclarationNode -> {
        this.variable == other.variable &&
          this.variableType == other.variableType &&
          this.expression == other.expression
      }
      else -> super.equals(other)
    }
  }

  override fun hashCode(): Int {
    var result = variable.hashCode()
    result = 31 * result + variableType.hashCode()
    result = 31 * result + expression.hashCode()
    result = 31 * result + position.hashCode()
    return result
  }
}
