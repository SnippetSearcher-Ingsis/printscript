import ast.AST
import ast.ASTVisitor
import ast.DoubleExpression
import ast.Literal

class EvaluationVisitor : ASTVisitor {
    private var result: Any? = null // Result can be of any type

    override fun visit(node: AST): AST {
        when (node) {
            is Literal<*> -> {
                result = node.value
            }

            is DoubleExpression -> {
                node.left.accept(this)
                val left = result
                node.right.accept(this)
                val right = result

                if (left is Double && right is Double) {
                    result = when (node.operator) {
                        "+" -> left + right
                        "-" -> left - right
                        "*" -> left * right
                        "/" -> left / right
                        else -> throw IllegalArgumentException("Unknown operator: ${node.operator}")
                    }
                } else {
                    throw IllegalArgumentException("Operands must be Doubles for arithmetic operations")
                }
            }
            // Add more visit methods for other node types as needed
            else -> throw IllegalArgumentException("Unknown node type")
        }

        return AST::class
    }

    fun getResult(): Any? {
        return result
    }
}
