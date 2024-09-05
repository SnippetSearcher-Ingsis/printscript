import catchable.ICatchable
import node.ASTNode

/**
 * Interpreter that catches errors and logs them, without throwing them.
 * @param interpreter The interpreter to wrap.
 */
class CatchableInterpreter(private val interpreter: IInterpreter) : IInterpreter, ICatchable {
  private var exception: Exception? = null

  override fun interpret(iterator: Iterator<ASTNode>) {
    try {
      exception = null
      interpreter.interpret(iterator)
    } catch (e: Exception) {
      exception = e
    }
  }

  override fun hasException(): Boolean = exception != null

  override fun getException(): Exception? = exception
}
