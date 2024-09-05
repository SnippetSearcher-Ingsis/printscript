import node.ASTNode
import tracer.Tracer
import util.Context
import visitor.TracingStrategy
import visitor.Visitor

/**
 * Interpreter that logs the execution of the program.
 * @param print If true, the interpreter will print the execution of the program to the standard output.
 */
class TracingInterpreter(tracer: Tracer, private val print: Boolean = true) : IInterpreter {
  private val context = Context()

  private val strategy = TracingStrategy(tracer, print = print)

  private val visitor = Visitor(context, strategy)

  override fun interpret(iterator: Iterator<ASTNode>) {
    while (iterator.hasNext()) {
      val node = iterator.next()
      node.accept(visitor)
    }
  }
}
