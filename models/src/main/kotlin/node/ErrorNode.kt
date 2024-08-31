package node

class ErrorNode(val error: String) : ASTNode {
  override fun accept(visitor: ASTVisitor) {
    visitor.visit(this)
  }

  override fun toString(): String {
    return "ErrorNode(error='$error')"
  }
}
