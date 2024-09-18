package com.printscript.interpreter.strategy

data object PreConfiguredProviders {
  val VERSION_1_0 = StrategyProvider builder {
    this addStrategy AssignationStrategy()
    this addStrategy DoubleExpressionStrategy()
    this addStrategy LiteralStrategy()
    this addStrategy PrintStatementStrategy()
    this addStrategy VariableDeclarationStrategy()
    this addStrategy VariableStrategy()
  }

  val VERSION_1_1 = VERSION_1_0 + StrategyProvider.builder {
    this addStrategy ConstantDeclarationStrategy()
    this addStrategy ConstantStrategy()
    this addStrategy IfElseStrategy()
    this addStrategy ReadEnvStrategy()
    this addStrategy ReadInputStrategy()
    this addStrategy CommentStrategy()
  }
}
