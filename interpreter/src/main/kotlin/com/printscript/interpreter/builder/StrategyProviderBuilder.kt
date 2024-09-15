package com.printscript.interpreter.builder

import com.printscript.interpreter.Interpreter
import com.printscript.interpreter.strategy.Strategy
import com.printscript.interpreter.strategy.StrategyProvider
import com.printscript.models.node.ASTNode

/**
 * A provider of [Strategy] to use in the [Interpreter].
 */
class StrategyProviderBuilder {
  private val strategies = mutableMapOf<Class<out ASTNode>, Strategy<out ASTNode>>()

  /**
   * Adds a [Strategy] for the given [ASTNode] type. The strategy generic must be the same as the node type.
   * If two or more strategies are added for the same node type, only the last one will be applied.
   */
  inline infix fun <reified T> addStrategy(strategy: Strategy<T>) where T : ASTNode {
    addStrategy(T::class.java, strategy)
  }

  /**
   * Adds a [Strategy] for the given [ASTNode] type. In this case, the node type is passed as a parameter.
   */
  fun <T> addStrategy(type: Class<T>, strategy: Strategy<T>) where T : ASTNode {
    strategies[type] = strategy
  }

  /**
   * Builds the [StrategyProvider] with the added [Strategy].
   */
  fun build(): StrategyProvider = StrategyProvider implementation strategies
}
