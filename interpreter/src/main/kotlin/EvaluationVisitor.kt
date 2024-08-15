import node.*

class EvaluationVisitor : ASTVisitor {
    private val context = mutableMapOf<String, Any>()

    override fun visit(node: DoubleExpressionNode) {


    }

    override fun visit(node: LiteralNode<*>) {

    }

    override fun visit(node: PrintStatementNode) {

    }

    override fun visit(node: VariableDeclarationNode) {
        // Execution can be Literal or Double Expression
        if (context.containsKey(node.variable)) throw Error("Identifier already used.")
        when (node.expression) {
            is LiteralNode<*> -> context[node.variable!!] = node.expression
            is DoubleExpressionNode -> {
                val execution = node.expression as DoubleExpressionNode
                when(execution.operator) {
                    "+" -> "si"
                }
            }
        }
    }

    override fun visit(node: AssignationNode) {

    }
}
