import node.*

class EvaluationVisitor : ASTVisitor {
    override fun visit(node: DoubleExpressionNode) {}

    override fun visit(node: LiteralNode<*>) {}

    override fun visit(node: PrintStatementNode) {
        Handler print node.expression
    }

    override fun visit(node: VariableDeclarationNode) {
        // Execution can be Literal or Double Expression
        val value = Solver getValue node.expression
        Handler.declareValue(node.variable!!, value)
    }

    override fun visit(node: AssignationNode) {
        val value = Solver getValue node.expression
        Handler.assignValue(node.variable!!, value)
    }
}
