package com.printscript.interpreter

import com.printscript.models.catchable.Catchable
import com.printscript.models.node.ASTNode

/**
 * Interpreter that catches errors and logs them, without throwing them.
 * @param interpreter The interpreter to wrap.
 */
class CatchableInterpreter(private val interpreter: Interpreter) :
  Interpreter,
  Catchable {
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
