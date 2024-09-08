package com.printscript.interpreter

import com.printscript.interpreter.strategy.TracingStrategy
import com.printscript.interpreter.tracer.PrintTracer
import com.printscript.interpreter.util.Context
import com.printscript.models.node.ASTNode

/**
 * Interpreter that evaluates the AST.
 */
class PrintInterpreter : Interpreter {
  private val context = Context()

  private val strategy = TracingStrategy(PrintTracer())

  override fun interpret(iterator: Iterator<ASTNode>) {
    iterator.forEachRemaining { strategy.visit(context, it) }
  }
}
