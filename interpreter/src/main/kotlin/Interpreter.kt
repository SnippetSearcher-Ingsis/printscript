import node.ASTNode
import visitor.EvaluationVisitor

/**
 * Interpreter that evaluates the AST.
 */
class Interpreter : IInterpreter {
  override fun interpret(nodes: List<ASTNode>) {
    val context = Context()
    val visitor = EvaluationVisitor(context)
    nodes.forEach { it.accept(visitor) }
  }
}
