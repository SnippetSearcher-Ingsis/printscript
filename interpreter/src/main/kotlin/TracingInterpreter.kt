import logger.ILog
import logger.Logger
import node.ASTNode
import visitor.EvaluationVisitor
import visitor.TracingVisitor

/**
 * Interpreter that logs the execution of the program.
 */
class TracingInterpreter : IInterpreter, ILog {
  private val logger = Logger()

  private val context = Context()

  private val visitor = TracingVisitor(context, EvaluationVisitor(context), logger)

  override fun interpret(nodes: List<ASTNode>) {
    nodes.forEach { it.accept(visitor) }
  }

  override fun getLog(): List<String> = logger.getLog()
}
