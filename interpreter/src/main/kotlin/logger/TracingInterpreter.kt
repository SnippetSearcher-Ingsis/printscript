package logger

import interpreter.Context
import interpreter.EvaluationVisitor
import interpreter.IInterpreter
import node.ASTNode

/**
 * Interpreter that logs the execution of the program.
 */
class TracingInterpreter : IInterpreter, ILog {
  private val logger = Logger()

  private val visitor = TracingVisitor(EvaluationVisitor(), logger)

  override fun interpret(nodes: List<ASTNode>) {
    Context.clear()
    nodes.forEach { it.accept(visitor) }
  }

  override fun getLog(): List<String> = logger.getLog()
}
