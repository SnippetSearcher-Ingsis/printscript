package com.printscript.interpreter.strategy

import com.printscript.interpreter.Interpreter
import com.printscript.interpreter.builder.StrategyProviderBuilder
import com.printscript.models.node.ASTNode

/**
 * A provider of [Strategy] to use in the [Interpreter].
 */
sealed interface StrategyProvider {
  /**
   * Gets the [Strategy] for the given [ASTNode] type. The generics guarantee the type safety.
   */
  infix fun <T> getStrategyFor(node: T): Strategy<T>? where T : ASTNode

  /**
   * Combines this [StrategyProvider] with another [StrategyProvider].
   */
  operator fun plus(other: StrategyProvider): StrategyProvider

  /**
   * Returns an iterator over the [Pair] of [Class] and [Strategy] of this [StrategyProvider].
   */
  fun iterator(): Iterator<Pair<Class<out ASTNode>, Strategy<out ASTNode>>>

  companion object {
    /**
     * Creates a new [StrategyProvider] with the given [Strategy].
     */
    infix fun builder(block: StrategyProviderBuilder.() -> Unit): StrategyProvider {
      return StrategyProviderBuilder().apply(block).build()
    }

    internal infix fun implementation(strategies: Map<Class<out ASTNode>, Strategy<out ASTNode>>): StrategyProvider {
      return StrategyProviderImplementation(strategies)
    }
  }

  private class StrategyProviderImplementation(private val strategies: Map<Class<out ASTNode>, Strategy<out ASTNode>>) :
    StrategyProvider {
    @Suppress("UNCHECKED_CAST")
    override fun <T> getStrategyFor(node: T): Strategy<T>? where T : ASTNode {
      return strategies[node::class.java] as Strategy<T>?
    }

    override fun plus(other: StrategyProvider): StrategyProvider {
      val map = mutableMapOf<Class<out ASTNode>, Strategy<out ASTNode>>()
      strategies.forEach { (k, v) -> map[k] = v }
      other.iterator().forEach { (k, v) -> map[k] = v }
      return StrategyProviderImplementation(map.toMap())
    }

    override fun iterator() = strategies.map { it.toPair() }.iterator()
  }
}
