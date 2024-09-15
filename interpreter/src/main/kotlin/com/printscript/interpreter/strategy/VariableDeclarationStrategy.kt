package com.printscript.interpreter.strategy

import com.printscript.interpreter.DeclarationException
import com.printscript.interpreter.ReferenceException
import com.printscript.interpreter.modifier.Variable
import com.printscript.interpreter.util.Services
import com.printscript.models.node.VariableDeclarationNode

class VariableDeclarationStrategy : Strategy<VariableDeclarationNode> {
  override fun visit(services: Services, node: VariableDeclarationNode): Any? {
    val value = services.visit(services, node.expression)
    when {
      value == null -> throw ReferenceException("Value is null")
      node.identifier in services.context -> throw DeclarationException("Identifier ${node.identifier} already exists.")
      !validType(value, node.valueType) -> throw DeclarationException("Invalid type for ${node.identifier}")
      else -> services.context.put(node.identifier, Variable(node.valueType, value))
    }
    return null
  }

  private fun validType(value: Any, type: String): Boolean {
    return when (type) {
      "number" -> value is Number
      "string" -> value is String
      "boolean" -> value is Boolean
      else -> false
    }
  }
}
