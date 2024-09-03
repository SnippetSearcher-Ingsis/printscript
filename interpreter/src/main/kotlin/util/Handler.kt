package util

import AssignationException
import DeclarationException
import node.ASTNode
import node.AssignationNode
import node.ConstantDeclarationNode
import node.IfElseNode
import node.VariableDeclarationNode
import visitor.EvaluationStrategy
import visitor.Visitor

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

  fun declareValue(context: Context, node: ConstantDeclarationNode) {
    val key = node.variable
    val value = getValue(context, key, node.variableType, node.expression)
    context.putConstant(key, value)
  }

  fun assignValue(context: Context, node: AssignationNode) {
    val key = node.variable!!
    val value = Solver.getValue(context, node.expression)
    when {
      !(context has key) -> throw AssignationException("$key is not declared.")
      context isConstant key -> throw AssignationException("$key is a constant.")
      !((context get key)!! hasSameTypeAs value) -> throw AssignationException("Type mismatch. Cannot assign $value to $key.")
      else -> context.put(key, value)
    }
  }

  fun runBranch(context: Context, node: IfElseNode) {
    val condition = node.condition.value
    val bool: Boolean = when {
      context.has(condition.toString()) -> {
        val value = context.get(condition.toString())
        if (value is Boolean) value
        else throw DeclarationException("$condition is not a boolean.")
      }
      condition is Boolean -> condition
      else -> throw DeclarationException("$condition is not a boolean or is not defined.")
    }
    val branch = if (bool) node.ifBranch else node.elseBranch
    val branchContext = context.clone()
    branch.forEach { it.accept(Visitor(branchContext, EvaluationStrategy)) }
    context.merge(branchContext)
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
