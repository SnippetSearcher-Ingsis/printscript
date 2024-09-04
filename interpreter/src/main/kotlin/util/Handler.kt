package util

import AssignationException
import DeclarationException
import modifier.Variable
import node.ASTNode
import node.AssignationNode
import node.VariableDeclarationNode
import node.VariableNode

internal object Handler {
  fun print(context: Context, node: ASTNode) {
    val value = Solver.getValue(context, node)
    println(value)
  }

  fun declareValue(context: Context, node: VariableDeclarationNode) {
    val key = node.variable
    val value = getValue(context, key, node.variableType, node.expression)
    context.put(key, Variable(node.variableType, value))
  }

  fun assignValue(context: Context, node: AssignationNode) {
    val key = node.variable!!
    val value = Solver.getValue(context, node.expression)
    val type = getType(value)
    when {
      !(context has key) -> throw AssignationException("$key is not declared.")
      else -> context.put(key, Variable(type, value))
    }
  }

  fun declareVariable(context: Context, node: VariableNode) {
    context.put(node.name, Variable(node.type, null))
  }

  private fun getValue(context: Context, key: String, type: String, expression: ASTNode): Any {
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
