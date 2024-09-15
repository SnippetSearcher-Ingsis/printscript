package com.printscript.interpreter.strategy

import com.printscript.interpreter.OperationException
import com.printscript.interpreter.ReferenceException
import com.printscript.interpreter.util.Services
import com.printscript.models.node.DoubleExpressionNode

class DoubleExpressionStrategy : Strategy<DoubleExpressionNode> {
  override fun visit(services: Services, node: DoubleExpressionNode): Any {
    val left = services.visit(services, node.left)
    val right = services.visit(services, node.right)
    return when {
      left == null || right == null -> throw ReferenceException("Null value in expression.")
      node.operator == "+" -> add(left, right)
      node.operator == "-" -> subtract(left, right)
      node.operator == "*" -> multiply(left, right)
      node.operator == "/" -> divide(left, right)
      else -> throw OperationException("Operator ${node.operator} not supported.")
    }
  }

  private fun round(number: Number): Number {
    return when {
      number.toDouble() % 1 == 0.0 -> number.toInt()
      else -> number
    }
  }

  private fun add(a: Any, b: Any): Any {
    return when {
      a is Number && b is Number -> round(a.toDouble() + b.toDouble())
      a is Number && b is String -> a.toString() + b
      a is String && b is Number -> a + b.toString()
      a is String && b is String -> a + b
      a is String && b is Boolean -> a + b.toString()
      a is Boolean && b is String -> a.toString() + b
      else -> throw OperationException("Operation $a + $b not supported.")
    }
  }

  private fun subtract(a: Any, b: Any): Any {
    return when {
      a is Number && b is Number -> round(a.toDouble() - b.toDouble())
      else -> throw OperationException("Operation $a - $b not supported.")
    }
  }

  private fun multiply(a: Any, b: Any): Any {
    return when {
      a is Number && b is Number -> round(a.toDouble() * b.toDouble())
      else -> throw OperationException("Operation $a * $b not supported.")
    }
  }

  private fun divide(a: Any, b: Any): Any {
    return when {
      a is Number && b is Number -> round(a.toDouble() / b.toDouble())
      else -> throw OperationException("Operation $a / $b not supported.")
    }
  }
}
