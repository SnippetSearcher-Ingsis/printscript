package com.printscript.interpreter.strategy

import com.printscript.models.node.PrintStatementNode

val printStatementStrategy = Strategy<PrintStatementNode> { services, node ->
  val value = services.visit(services, node.expression)
  services.output.write(value.toString())
}
