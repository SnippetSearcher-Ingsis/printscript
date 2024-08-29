import logger.ILog
import logger.Logger
import node.ASTNode
import util.Context
import visitor.TracingStrategy
import visitor.Visitor

/**
 * Interpreter that logs the execution of the program.
 * @param print If true, the interpreter will print the execution of the program to the standard output.
 */
class TracingInterpreter(private val print: Boolean = true) : IInterpreter, ILog {
  private val logger = Logger()

  private val context = Context()

  private val strategy = TracingStrategy(logger, print = print)

  private val visitor = Visitor(context, strategy)

  override fun interpret(nodes: List<ASTNode>) {
    nodes.forEach { it.accept(visitor) }
  }

  override fun getLog(): List<String> = logger.getLog()
}
