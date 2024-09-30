package com.printscript.interpreter.strategy

import com.printscript.interpreter.OperationException
import com.printscript.interpreter.ReferenceException
import com.printscript.models.node.DoubleExpressionNode

val doubleExpressionStrategy = Strategy<DoubleExpressionNode> { services, node ->
  val left = services.visit(services, node.left)
  val right = services.visit(services, node.right)
  when {
    left == null || right == null -> throw ReferenceException("Null value in expression.")
    node.operator == "+" -> add(left, right)
    node.operator == "-" -> subtract(left, right)
    node.operator == "*" -> multiply(left, right)
    node.operator == "/" -> divide(left, right)
    else -> throw OperationException("Operator ${node.operator} not supported.")
  }
}

private val round = { number: Number ->
  when {
    number.toDouble() % 1 == 0.0 -> number.toInt()
    else -> number
  }
}

private val add = { a: Any, b: Any ->
  when {
    a is Number && b is Number -> round(a.toDouble() + b.toDouble())
    a is Number && b is String -> a.toString() + b
    a is String && b is Number -> a + b.toString()
    a is String && b is String -> a + b
    a is String && b is Boolean -> a + b.toString()
    a is Boolean && b is String -> a.toString() + b
    else -> throw OperationException("Operation $a + $b not supported.")
  }
}

private val subtract = { a: Any, b: Any ->
  when {
    a is Number && b is Number -> round(a.toDouble() - b.toDouble())
    else -> throw OperationException("Operation $a - $b not supported.")
  }
}

private val multiply = { a: Any, b: Any ->
  when {
    a is Number && b is Number -> round(a.toDouble() * b.toDouble())
    else -> throw OperationException("Operation $a * $b not supported.")
  }
}

private val divide = { a: Any, b: Any ->
  when {
    a is Number && b is Number -> round(a.toDouble() / b.toDouble())
    else -> throw OperationException("Operation $a / $b not supported.")
  }
}
