package com.printscript.interpreter.strategy

import com.printscript.interpreter.util.Services
import com.printscript.models.node.PrintStatementNode

class PrintStatementStrategy : Strategy<PrintStatementNode> {
  override fun visit(services: Services, node: PrintStatementNode): Any? {
    val value = services.visit(services, node.expression)
    services.output.write(value.toString())
    return null
  }
}
