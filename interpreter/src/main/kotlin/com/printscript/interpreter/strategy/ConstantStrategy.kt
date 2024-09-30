package com.printscript.interpreter.strategy

import com.printscript.interpreter.DeclarationException
import com.printscript.interpreter.modifier.Constant
import com.printscript.models.node.ConstantNode

val constantStrategy = Strategy<ConstantNode> { services, node ->
  when {
    node.identifier in services.context -> throw DeclarationException("Identifier ${node.identifier} already exists.")
    else -> services.context.put(node.identifier, Constant(node.valueType, null))
  }
}
