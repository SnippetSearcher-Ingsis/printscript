package com.printscript.interpreter.strategy

object PreConfiguredProviders {
  val VERSION_1_0 = StrategyProvider.builder {
    add strategy AssignationStrategy()
    add strategy DoubleExpressionStrategy()
    add strategy ErrorStrategy()
    add strategy LiteralStrategy()
    add strategy PrintStatementStrategy()
    add strategy VariableDeclarationStrategy()
    add strategy VariableStrategy()
  }

  val VERSION_1_1 = VERSION_1_0 + StrategyProvider.builder {
    add strategy ConstantDeclarationStrategy()
    add strategy ConstantStrategy()
    add strategy IfElseStrategy()
    add strategy ReadEnvStrategy()
    add strategy ReadInputStrategy()
  }
}
