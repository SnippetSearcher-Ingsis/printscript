import node.ASTNode
import visitor.EvaluationVisitor

/**
 * Interpreter that evaluates the AST.
 */
class Interpreter : IInterpreter {
  override fun interpret(nodes: List<ASTNode>) {
    Context.clear()
    val visitor = EvaluationVisitor()
    nodes.forEach { node ->
      node.accept(visitor)
    }
  }
}
