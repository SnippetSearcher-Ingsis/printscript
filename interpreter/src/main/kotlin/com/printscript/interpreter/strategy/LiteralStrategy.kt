package com.printscript.interpreter.strategy

import com.printscript.interpreter.ReferenceException
import com.printscript.interpreter.util.Services
import com.printscript.models.node.LiteralNode

class LiteralStrategy : Strategy<LiteralNode<*>> {
  override fun visit(services: Services, node: LiteralNode<*>): Any? {
    return when {
      node.value is String -> handleString(services, node.value as String)
      else -> node.value
    }
  }

  private fun handleString(services: Services, identifier: String): Any? {
    return when {
      identifier.startsWith("\"") -> identifier.replace("\"", "")
      identifier.startsWith("'") -> identifier.replace("'", "")
      identifier !in services.context -> throw ReferenceException("Identifier $identifier not found.")
      else -> (services.context get identifier)?.value
    }
  }
}
