import catchable.ICatchable
import logger.ILog
import node.ASTNode

/**
 * Interpreter that catches errors and logs them, without throwing them.
 * @param print If true, the interpreter will print the execution of the program to the standard output.
 */
class CatchableTracingInterpreter(private val print: Boolean = true) : IInterpreter, ILog, ICatchable {
  private val interpreter = TracingInterpreter(print = print)

  private var exception: Exception? = null

  override fun interpret(iterator: Iterator<ASTNode>) {
    try {
      exception = null
      interpreter.interpret(iterator)
    } catch (e: Exception) {
      exception = e
    }
  }

  override fun getLog(): List<String> = interpreter.getLog()

  override fun hasException(): Boolean = exception != null

  override fun getException(): Exception? = exception
}
