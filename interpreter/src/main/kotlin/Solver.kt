import exception.OperationException
import exception.ReferenceException
import node.ASTNode
import node.DoubleExpressionNode
import node.LiteralNode

object Solver {
  infix fun getValue(node: ASTNode): Any {
    return when (node) {
      is LiteralNode<*> -> {
        when {
          node.value is String && (node.value as String).startsWith("\"") ->
            (node.value as String).replace("\"", "")

          node.value is String && (node.value as String).startsWith("'") ->
            (node.value as String).replace("'", "")

          node.value is String -> (Context get node.value as String)
            ?: throw ReferenceException(node.value as String)

          else -> node.value!!
        }
      }

      is DoubleExpressionNode -> {
        val a = getValue(node.left)
        val b = getValue(node.right)
        when (node.operator) {
          "+" -> add(a, b)
          "-" -> subtract(a, b)
          "*" -> multiply(a, b)
          "/" -> divide(a, b)
          else -> throw OperationException("$a ${node.operator} $b")
        }
      }

      else -> throw OperationException(node.toString())
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
      else -> throw OperationException("$a + $b")
    }
  }

  private fun subtract(a: Any, b: Any): Any {
    return when {
      a is Number && b is Number -> round(a.toDouble() - b.toDouble())
      else -> throw OperationException("$a - $b")
    }
  }

  private fun multiply(a: Any, b: Any): Any {
    return when {
      a is Number && b is Number -> round(a.toDouble() * b.toDouble())
      else -> throw OperationException("$a * $b")
    }
  }

  private fun divide(a: Any, b: Any): Any {
    return when {
      a is Number && b is Number -> round(a.toDouble() / b.toDouble())
      else -> throw OperationException("$a / $b")
    }
  }
}
