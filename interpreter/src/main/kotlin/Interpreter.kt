import node.ASTNode
import node.ASTVisitor
import util.Context
import visitor.EvaluationStrategy
import visitor.Visitor

/**
 * Interpreter that evaluates the AST.
 */
class Interpreter : IInterpreter {
  private val context = Context()

  private val strategy = EvaluationStrategy

  private val visitor: ASTVisitor = Visitor(context, strategy)

  override fun interpret(iterator: Iterator<ASTNode>) {
    while (iterator.hasNext()) {
      val node = iterator.next()
      node.accept(visitor)
    }
  }
}
