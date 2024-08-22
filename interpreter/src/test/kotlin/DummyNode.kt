import node.ASTNode
import node.ASTVisitor

class DummyNode : ASTNode {
  override fun accept(visitor: ASTVisitor) {}
}
