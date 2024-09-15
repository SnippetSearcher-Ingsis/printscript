package com.printscript.interpreter.builder

import com.printscript.interpreter.BuilderException
import com.printscript.interpreter.Interpreter
import com.printscript.interpreter.input.Input
import com.printscript.interpreter.output.Output
import com.printscript.interpreter.strategy.StrategyProvider
import kotlin.jvm.Throws

/**
 * A builder for the [Interpreter].
 */
class InterpreterBuilder {
  private var input: Input? = null

  private var output: Output? = null

  private var provider: StrategyProvider? = null

  /**
   * Sets the [Input] for the [Interpreter].
   */
  infix fun setInput(input: Input) {
    this.input = input
  }

  /**
   * Sets the [Output] for the [Interpreter].
   */
  infix fun setOutput(output: Output) {
    this.output = output
  }

  /**
   * Sets the [StrategyProvider] for the [Interpreter].
   */
  infix fun setProvider(provider: StrategyProvider) {
    this.provider = provider
  }

  /**
   * Builds the [Interpreter] with the set [Input], [Output] and [StrategyProvider].
   */
  @Throws(BuilderException::class)
  fun build(): Interpreter {
    val input = input
    val output = output
    val provider = provider
    when {
      input == null -> throw BuilderException("Input is required")
      output == null -> throw BuilderException("Output is required")
      provider == null -> throw BuilderException("Strategy provider is required")
      else -> return Interpreter.implementation(input, output, provider)
    }
  }
}
