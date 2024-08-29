import logger.ILog
import logger.Logger
import node.ASTNode
import util.Context
import visitor.EvaluationVisitor
import visitor.TracingVisitor

/**
 * Interpreter that logs the execution of the program.
 * @param print If true, the interpreter will print the execution of the program to the standard output.
 */
class TracingInterpreter(private val print: Boolean = true) : IInterpreter, ILog {
  private val logger = Logger()

  private val context = Context()

  private val visitor = TracingVisitor(context, EvaluationVisitor(context), logger, print)

  override fun interpret(nodes: List<ASTNode>) {
    nodes.forEach { it.accept(visitor) }
  }

  override fun getLog(): List<String> = logger.getLog()
}
