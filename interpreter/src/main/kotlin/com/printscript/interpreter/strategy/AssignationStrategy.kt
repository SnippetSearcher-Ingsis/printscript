package com.printscript.interpreter.strategy

import com.printscript.interpreter.AssignationException
import com.printscript.interpreter.ReferenceException
import com.printscript.interpreter.modifier.Constant
import com.printscript.interpreter.modifier.Variable
import com.printscript.interpreter.util.Services
import com.printscript.models.node.AssignationNode

class AssignationStrategy : Strategy<AssignationNode> {
  override fun visit(services: Services, node: AssignationNode): Any? {
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
