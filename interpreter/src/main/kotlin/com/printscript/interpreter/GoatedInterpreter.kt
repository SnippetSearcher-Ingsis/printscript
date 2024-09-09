package com.printscript.interpreter

import com.printscript.interpreter.input.Input
import com.printscript.interpreter.output.Output
import com.printscript.interpreter.strategy.GoatedStrategy
import com.printscript.interpreter.util.Context
import com.printscript.interpreter.util.Services
import com.printscript.models.node.ASTNode

/**
 * Interpreter that logs the execution of the program.
 * @param input the input to use.
 * @param output the output to use.
 */
class GoatedInterpreter(input: Input, output: Output) : Interpreter {
  private val context = Context()

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
