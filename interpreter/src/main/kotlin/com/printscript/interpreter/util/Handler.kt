package com.printscript.interpreter.util

import com.printscript.interpreter.AssignationException
import com.printscript.interpreter.DeclarationException
import com.printscript.interpreter.OperationException
import com.printscript.interpreter.modifier.Variable

internal object Handler {
  fun print(context: Context, node: com.printscript.models.node.ASTNode) {
    val value = Solver.getValue(context, node)
    println(value)
  }

  fun declareValue(context: Context, node: com.printscript.models.node.VariableDeclarationNode) {
    val key = node.variable
    val value = getValue(context, key, node.variableType, node.expression)
    context.put(key, Variable(node.variableType, value))
  }

  fun assignValue(context: Context, node: com.printscript.models.node.AssignationNode) {
    val key = node.variable!!
    val value = Solver.getValue(context, node.expression)
    val type = getType(value)
    when {
      !(context has key) -> throw AssignationException("$key is not declared.")
      else -> context.put(key, Variable(type, value))
    }
  }

  fun declareVariable(context: Context, node: com.printscript.models.node.VariableNode) {
    context.put(node.name, Variable(node.type, null))
  }

  fun error(node: com.printscript.models.node.ErrorNode) {
    when {
      node.error != "NODE_ERROR_BACKDOOR" -> throw OperationException(node.error)
    }
  }

  private fun getValue(context: Context, key: String, type: String, expression: com.printscript.models.node.ASTNode): Any {
    val value = Solver.getValue(context, expression)
    when {
      context has key -> throw DeclarationException("$key is already declared.")
      value is Boolean && type.lowercase() != "boolean" -> throw DeclarationException("$value is not a ${type.lowercase()}.")
      value is Number && type.lowercase() != "number" -> throw DeclarationException("$value is not a ${type.lowercase()}.")
      value is String && type.lowercase() != "string" -> throw DeclarationException("$value is not a ${type.lowercase()}.")
    }
    return value
  }

  private fun getType(value: Any): String {
    return when (value) {
      is Boolean -> "boolean"
      is Number -> "number"
      is String -> "string"
      else -> throw AssignationException("Cannot assign $value.")
    }
  }
}
