import node.ASTVisitor
import node.AssignationNode
import node.DoubleExpressionNode
import node.LiteralNode
import node.PrintStatementNode
import node.VariableDeclarationNode
import rule.Rule

class FormatterVisitor(private val rules: List<Rule>, private val outputCode: StringBuilder) :
    ASTVisitor {
    override fun visit(node: DoubleExpressionNode) {
    }

    override fun visit(node: LiteralNode<*>) {
        TODO("Not yet implemented")
    }

    override fun visit(node: PrintStatementNode) {
        TODO("Not yet implemented")
    }

    override fun visit(node: VariableDeclarationNode) {
        TODO("Not yet implemented")
    }

    override fun visit(node: AssignationNode) {
        TODO("Not yet implemented")
    }
}
