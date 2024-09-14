package com.printscript.interpreter

import com.printscript.interpreter.builders.InterpreterBuilder
import com.printscript.interpreter.input.Input
import com.printscript.interpreter.output.Output
import com.printscript.interpreter.strategy.StrategyProvider
import com.printscript.interpreter.util.Context
import com.printscript.interpreter.util.Services
import com.printscript.models.node.ASTNode

/**
 * Configurable PrintScript Interpreter.
 */
sealed interface Interpreter {
  /**
   * Interprets an iterator of [ASTNode] recursively.
   */
  infix fun interpret(iterator: Iterator<ASTNode>)

  companion object {
    /**
     * Creates a new interpreter with the given [Input], [Output] and [StrategyProvider].
     */
    infix fun builder(block: InterpreterBuilder.() -> Unit): Interpreter {
      return InterpreterBuilder().apply(block).build()
    }

    internal fun implementation(input: Input, output: Output, provider: StrategyProvider): Interpreter {
      return Implementation(input, output, provider)
    }
  }

  /**
   * The internal implementation of the [Interpreter].
   */
  private class Implementation(input: Input, output: Output, private val provider: StrategyProvider) : Interpreter {
    private val context = Context()

    private val services = Services(context, input, output) { services: Services, node: ASTNode ->
      val strategy = (provider getStrategyFor node)
        ?: throw UnsupportedNodeException("Unsupported node: ${node::class.simpleName}")
      strategy.visit(services, node)
    }

    override fun interpret(iterator: Iterator<ASTNode>) {
      iterator.forEachRemaining { node ->
        val strategy = (provider getStrategyFor node)
          ?: throw UnsupportedNodeException("Unsupported node: ${node::class.simpleName}")
        strategy.visit(services, node)
      }
    }
  }
}
