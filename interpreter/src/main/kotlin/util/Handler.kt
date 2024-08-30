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
      value is Boolean && type != "boolean" -> throw DeclarationException("$value is not a boolean.")
      value is Number && type != "number" -> throw DeclarationException("$value is not a number.")
      value is String && type != "string" -> throw DeclarationException("$value is not a string.")
      else -> context.addOrUpdate(key, value)
    }
  }

  fun assignValue(context: Context, node: AssignationNode) {
    val key = node.variable!!
    val value = Solver.getValue(context, node.expression)
    when {
      !(context has key) -> throw AssignationException("$key is not declared.")
      !((context get key)!! hasSameTypeAs value) -> throw AssignationException("Type mismatch. Cannot assign $value to $key.")
      else -> context.addOrUpdate(key, value)
    }
  }

  private infix fun Any.hasSameTypeAs(b: Any): Boolean {
    return this::class == b::class || this is Number && b is Number
  }
}
