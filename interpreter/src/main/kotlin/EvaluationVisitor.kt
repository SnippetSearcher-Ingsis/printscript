import node.ASTNode
import node.ASTVisitor
import node.DoubleExpressionNode
import node.LiteralNode

class EvaluationVisitor : ASTVisitor {
    private var result: Any? = null // Result can be of any type

    override fun visit(node: ASTNode): ASTNode {
        when (node) {
            is LiteralNode<*> -> {
                result = node.value
            }

            is DoubleExpressionNode -> {
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

        return ASTNode::class
    }

    fun getResult(): Any? {
        return result
    }
}
