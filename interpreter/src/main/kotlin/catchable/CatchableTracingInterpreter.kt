package catchable

import interpreter.IInterpreter
import logger.ILog
import logger.TracingInterpreter
import node.ASTNode

/**
 * Interpreter that catches errors and logs them, without throwing them.
 */
class CatchableTracingInterpreter : IInterpreter, ILog, ICatchable {
  private val interpreter = TracingInterpreter()

  private var error: Error? = null

  override fun interpret(nodes: List<ASTNode>) {
    try {
      interpreter.interpret(nodes)
    } catch (e: Error) {
      error = e
    }
  }

  override fun getLog(): List<String> = interpreter.getLog()

  override fun hasError(): Boolean = error != null

  override fun getError(): Error? = error
}
