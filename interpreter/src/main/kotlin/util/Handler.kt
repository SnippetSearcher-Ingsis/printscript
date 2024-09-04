package util

import AssignationException
import DeclarationException
import node.ASTNode
import node.AssignationNode
import node.VariableDeclarationNode

internal object Handler {
  fun print(context: Context, node: ASTNode) {
    val value = Solver.getValue(context, node)
    println(value)
  }

  fun declareValue(context: Context, node: VariableDeclarationNode) {
    val key = node.variable
    val value = getValue(context, key, node.variableType, node.expression)
    context.put(key, value)
  }

  fun assignValue(context: Context, node: AssignationNode) {
    val key = node.variable!!
    val value = Solver.getValue(context, node.expression)
    when {
      !(context has key) -> throw AssignationException("$key is not declared.")
      !((context get key)!! hasSameTypeAs value) -> throw AssignationException("Type mismatch. Cannot assign $value to $key.")
      else -> context.put(key, value)
    }
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

  private infix fun Any.hasSameTypeAs(b: Any): Boolean {
    return this::class == b::class || this is Number && b is Number
  }
}
