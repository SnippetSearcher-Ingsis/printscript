package node

class ReadInputNode(val value: String) : ASTNode {
  override fun accept(visitor: ASTVisitor) {
    visitor.visit(this)
  }

  override fun toString(): String {
    return "ReadInputNode(value='$value')"
  }
}
