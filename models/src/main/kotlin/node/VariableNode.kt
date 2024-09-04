package node

data class VariableNode(
  val name: String,
  val type: String,
) : ASTNode {
  override fun accept(visitor: ASTVisitor) {
    visitor.visit(this)
  }
}
