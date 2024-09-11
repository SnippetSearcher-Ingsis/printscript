package com.printscript.interpreter

import com.printscript.interpreter.input.ConsoleInput
import com.printscript.interpreter.output.ConsoleOutput
import com.printscript.interpreter.strategy.GoatedStrategy
import com.printscript.interpreter.util.Context
import com.printscript.interpreter.util.Services
import com.printscript.models.node.ASTNode

/**
 * Interpreter that evaluates the AST.
 */
class PrintInterpreter : Interpreter {
  private val context = Context()

  private val input = ConsoleInput()

  private val output = ConsoleOutput()

  private val services: Services = Services(context, input, output) { services: Services, node: ASTNode ->
    strategy.visit(
      services,
      node
    )
  }

  private val strategy = GoatedStrategy()

  override fun interpret(iterator: Iterator<ASTNode>) {
    iterator.forEachRemaining { strategy.visit(services, it) }
  }
}
