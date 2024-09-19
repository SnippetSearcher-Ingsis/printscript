package com.printscript.interpreter.strategy

import com.printscript.interpreter.DeclarationException
import com.printscript.interpreter.modifier.Variable
import com.printscript.models.node.VariableNode

val variableStrategy = Strategy<VariableNode> { services, node ->
  when {
    node.identifier in services.context -> throw DeclarationException("Identifier ${node.identifier} already exists.")
    else -> services.context.put(node.identifier, Variable(node.valueType, null))
  }
}
