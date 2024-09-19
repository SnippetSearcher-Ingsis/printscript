package com.printscript.interpreter.strategy

object PreConfiguredProviders {
  val VERSION_1_0 = StrategyProvider builder {
    this addStrategy assignationStrategy
    this addStrategy doubleExpressionStrategy
    this addStrategy literalStrategy
    this addStrategy printStatementStrategy
    this addStrategy variableDeclarationStrategy
    this addStrategy variableStrategy
  }

  val VERSION_1_1 = VERSION_1_0 + StrategyProvider.builder {
    this addStrategy constantDeclarationStrategy
    this addStrategy constantStrategy
    this addStrategy ifElseStrategy
    this addStrategy readEnvStrategy
    this addStrategy readInputStrategy
    this addStrategy commentStrategy
  }
}
