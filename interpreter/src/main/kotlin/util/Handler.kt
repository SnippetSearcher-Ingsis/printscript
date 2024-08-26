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
    val type = node.variableType
    val value = Solver.getValue(context, node.expression)
    when {
      context has key -> throw DeclarationException("$key is already declared.")
      value is Number && type.lowercase() != "number" -> throw DeclarationException("$value is not a number.")
      value is String && type.lowercase() != "string" -> throw DeclarationException("$value is not a string.")
      else -> context.add(key, value)
    }
  }

  fun assignValue(context: Context, node: AssignationNode) {
    val key = node.variable!!
    val value = Solver.getValue(context, node.expression)
    when {
      context has key && (context get key)!! hasSameTypeAs value -> context.add(key, value)
      context has key -> throw AssignationException(key)
      else -> throw AssignationException(key)
    }
  }

  private infix fun Any.hasSameTypeAs(b: Any): Boolean {
    return this::class == b::class || this is Number && b is Number
  }
}
