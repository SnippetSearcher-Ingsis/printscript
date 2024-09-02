package node

class ReadEnvNode(val value: String) : ASTNode {
  override fun accept(visitor: ASTVisitor) {
    visitor.visit(this)
  }

  override fun toString(): String {
    return "ReadEnvNode(value='$value')"
  }
}
