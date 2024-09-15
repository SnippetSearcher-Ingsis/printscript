package com.printscript.interpreter.strategy

import com.printscript.interpreter.util.Services
import com.printscript.models.node.ErrorNode

class ErrorStrategy : Strategy<ErrorNode> {
  override fun visit(services: Services, node: ErrorNode): Any? {
    throw Exception(node.error)
  }
}
