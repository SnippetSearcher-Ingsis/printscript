package com.printscript.interpreter.strategy

import com.printscript.interpreter.AssignationException
import com.printscript.interpreter.ReferenceException
import com.printscript.interpreter.modifier.Constant
import com.printscript.interpreter.modifier.Variable
import com.printscript.models.node.AssignationNode

val assignationStrategy = Strategy<AssignationNode> { services, node ->
  val value = services.visit(services, node.expression)
  val modifier = (services.context get node.variable)
  when {
    value == null -> throw ReferenceException("Value for ${node.variable} is null.")
    node.variable !in services.context -> throw ReferenceException("Identifier ${node.variable} not found.")
    modifier is Constant && modifier.value != null -> throw AssignationException("Cannot assign to constant ${node.variable}.")
    !validType(value, modifier!!.type) -> throw AssignationException("Type mismatch for ${node.variable}.")
    modifier is Constant -> services.context.put(node.variable, Constant(modifier.type, value))
    modifier is Variable -> services.context.put(node.variable, Variable(modifier.type, value))
  }
}
