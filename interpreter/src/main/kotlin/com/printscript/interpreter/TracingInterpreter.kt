package com.printscript.interpreter

import com.printscript.interpreter.tracer.Tracer
import com.printscript.interpreter.util.Context
import com.printscript.interpreter.visitor.TracingStrategy
import com.printscript.interpreter.visitor.Visitor

/**
 * Interpreter that logs the execution of the program.
 * @param print If true, the interpreter will print the execution of the program to the standard output.
 */
class TracingInterpreter(tracer: Tracer, private val print: Boolean = true) : IInterpreter {
  private val context = Context()

  private val strategy = TracingStrategy(tracer, print = print)

  private val visitor = Visitor(context, strategy)

  override fun interpret(iterator: Iterator<com.printscript.models.node.ASTNode>) {
    while (iterator.hasNext()) {
      val node = iterator.next()
      node.accept(visitor)
    }
  }
}
