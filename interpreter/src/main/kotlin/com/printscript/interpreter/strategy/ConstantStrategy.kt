package com.printscript.interpreter.strategy

import com.printscript.interpreter.DeclarationException
import com.printscript.interpreter.modifier.Constant
import com.printscript.interpreter.util.Services
import com.printscript.models.node.ConstantNode

class ConstantStrategy : Strategy<ConstantNode> {
  override fun visit(services: Services, node: ConstantNode): Any? {
    when {
      node.identifier in services.context -> throw DeclarationException("Identifier ${node.identifier} already exists.")
      else -> services.context.put(node.identifier, Constant(node.valueType, null))
    }
    return null
  }
}
