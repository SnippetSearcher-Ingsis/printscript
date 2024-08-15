import node.ASTVisitor
import node.DoubleExpressionNode
import node.LiteralNode
import node.PrintStatementNode
import node.VariableDeclarationNode

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
        when (node.execution) {
            is LiteralNode<*> -> context[node.variable!!] = node.execution
            is DoubleExpressionNode -> {
                val execution = node.execution as DoubleExpressionNode
                when(execution.operator) {
                    "+" -> "si"
                }
            }
        }
    }
}
