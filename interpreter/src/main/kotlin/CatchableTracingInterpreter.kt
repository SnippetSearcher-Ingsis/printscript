import catchable.ICatchable
import logger.ILog
import node.ASTNode

/**
 * Interpreter that catches errors and logs them, without throwing them.
 */
class CatchableTracingInterpreter : IInterpreter, ILog, ICatchable {
  private val interpreter = TracingInterpreter()

  private var exception: Exception? = null

  override fun interpret(nodes: List<ASTNode>) {
    try {
      exception = null
      interpreter.interpret(nodes)
    } catch (e: Exception) {
      exception = e
    }
  }

  override fun getLog(): List<String> = interpreter.getLog()

  override fun hasException(): Boolean = exception != null

  override fun getException(): Exception? = exception
}
