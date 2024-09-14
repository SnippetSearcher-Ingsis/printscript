package com.printscript.interpreter.strategy

import com.printscript.interpreter.DeclarationException
import com.printscript.interpreter.modifier.Variable
import com.printscript.interpreter.util.Services
import com.printscript.models.node.VariableNode

class VariableStrategy : Strategy<VariableNode> {
  override fun visit(services: Services, node: VariableNode): Any? {
    when {
      node.identifier in services.context -> throw DeclarationException("Identifier ${node.identifier} already exists.")
      else -> services.context.put(node.identifier, Variable(node.valueType, null))
    }
    return null
  }
}
