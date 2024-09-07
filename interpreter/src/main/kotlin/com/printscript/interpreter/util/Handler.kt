package com.printscript.interpreter.util

import com.printscript.interpreter.AssignationException
import com.printscript.interpreter.DeclarationException
import com.printscript.interpreter.OperationException
import com.printscript.interpreter.modifier.Constant
import com.printscript.interpreter.modifier.Variable
import com.printscript.interpreter.visitor.Visitor
import com.printscript.interpreter.visitor.VisitorStrategy
import com.printscript.models.node.ASTNode
import com.printscript.models.node.AssignationNode
import com.printscript.models.node.ConstantDeclarationNode
import com.printscript.models.node.ConstantNode
import com.printscript.models.node.DeclarationNode
import com.printscript.models.node.ErrorNode
import com.printscript.models.node.IfElseNode
import com.printscript.models.node.VariableDeclarationNode
import com.printscript.models.node.VariableNode

internal object Handler {
  fun print(context: Context, node: ASTNode) {
    val value = Solver.getValue(context, node)
    println(value)
  }

  fun declareValue(context: Context, node: DeclarationNode) {
    val key = node.variable
    val value = when (node) {
      is VariableNode, is ConstantNode -> null
      else -> getValue(context, key, node.variableType, node.expression)
    }
    when (node) {
      is ConstantDeclarationNode -> context.put(key, Constant(node.variableType, value))
      is VariableDeclarationNode -> context.put(key, Variable(node.variableType, value))
      is VariableNode -> context.put(key, Variable(node.variableType, null))
      is ConstantNode -> context.put(key, Constant(node.variableType, null))
      else -> throw DeclarationException("Unknown $node type.")
    }
  }

  fun assignValue(context: Context, node: AssignationNode) {
    val key = node.variable!!
    val value = Solver.getValue(context, node.expression)
    val type = getType(value)
    if (!context.has(key)) {
      throw AssignationException("$key is not declared.")
    } else {
      context.put(key, Variable(type, value))
    }
  }

  fun runBranch(context: Context, node: IfElseNode, strategy: VisitorStrategy) {
    val bool = when (val condition = node.condition.value.toString()) {
      "true" -> true
      "false" -> false
      else -> {
        val stored = context.get(condition) ?: throw DeclarationException("$condition is not a boolean or is not defined.")
        if (stored.type.lowercase() == "boolean") stored.value as Boolean
        else throw DeclarationException("$condition is not a boolean.")
      }
    }
    val branch = if (bool) node.ifBranch else node.elseBranch
    val branchContext = context.clone()
    branch.forEach { it.accept(Visitor(branchContext, strategy)) }
    context.merge(branchContext)
  }

  fun error(node: ErrorNode) {
    when {
      node.error != "NODE_ERROR_BACKDOOR" -> throw OperationException(node.error)
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

  private fun getType(value: Any): String {
    return when (value) {
      is Boolean -> "boolean"
      is Number -> "number"
      is String -> "string"
      else -> throw AssignationException("Cannot assign $value.")
    }
  }
}
