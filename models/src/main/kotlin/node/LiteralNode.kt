package node

data class LiteralNode<T>(val value: T) : ASTNode {

  override fun accept(visitor: ASTVisitor) {
    return visitor.visit(this)
  }

  override fun toString(): String {
    return "LiteralNode(value=$value)"
  }

  override fun equals(other: Any?): Boolean {
    return when {
      other is LiteralNode<*> -> value == other.value
      else -> super.equals(other)
    }
  }

  override fun hashCode(): Int {
    return value?.hashCode() ?: 0
  }
}
