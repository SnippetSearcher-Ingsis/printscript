package com.printscript.interpreter.strategy

import com.printscript.interpreter.ReferenceException
import com.printscript.interpreter.util.Services
import com.printscript.models.node.PrintStatementNode

class PrintStatementStrategy : Strategy<PrintStatementNode> {
  override fun visit(services: Services, node: PrintStatementNode): Any? {
    when (val value = services.visit(services, node.expression)) {
      null -> throw ReferenceException("Value is null")
      else -> services.output.write(value.toString())
    }
    return null
  }
}
