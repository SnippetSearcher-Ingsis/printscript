package com.printscript.interpreter.strategy

import com.printscript.interpreter.ReferenceException
import com.printscript.interpreter.util.Services
import com.printscript.models.node.LiteralNode

class LiteralStrategy : Strategy<LiteralNode<*>> {
  override fun visit(services: Services, node: LiteralNode<*>): Any {
    return when {
      node.isReference() -> handleReference(services, node.value as String)
      node.value is String -> getLiteral(node.value as String)
      else -> node.value!!
    }
  }

  private fun LiteralNode<*>.isReference(): Boolean {
    val value = this.value
    return value is String && !value.startsWith("\"") && !value.startsWith("'")
  }

  private fun handleReference(services: Services, identifier: String): Any {
    return when {
      identifier !in services.context -> throw ReferenceException("Identifier $identifier not found.")
      else -> (services.context get identifier)?.value!!
    }
  }

  private fun getLiteral(string: String): String {
    return when {
      string.startsWith("\"") -> string.replace("\"", "")
      string.startsWith("'") -> string.replace("'", "")
      else -> string
    }
  }
}
