package com.printscript.interpreter

import com.printscript.interpreter.input.Input
import com.printscript.interpreter.strategy.TracingStrategy
import com.printscript.interpreter.tracer.Tracer
import com.printscript.interpreter.util.Context
import com.printscript.models.node.ASTNode

/**
 * Interpreter that logs the execution of the program.
 * @param tracer the tracer to use.
 */
class TracingInterpreter(tracer: Tracer, input: Input) : Interpreter {
  private val context = Context()

  private val strategy = TracingStrategy(tracer, input)

  override fun interpret(iterator: Iterator<ASTNode>) {
    iterator.forEachRemaining { strategy.visit(context, it) }
  }
}
