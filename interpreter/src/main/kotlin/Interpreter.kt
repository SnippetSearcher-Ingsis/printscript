import node.ASTNode
import util.Context
import visitor.EvaluationStrategy
import visitor.Visitor

/**
 * Interpreter that evaluates the AST.
 */
class Interpreter : IInterpreter {
  private val context = Context()

  private val strategy = EvaluationStrategy()

  private val visitor = Visitor(context, strategy)

  override fun interpret(nodes: List<ASTNode>) {
    nodes.forEach { it.accept(visitor) }
  }
}
