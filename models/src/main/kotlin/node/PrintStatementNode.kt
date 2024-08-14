package node

class PrintStatementNode(val expression: ASTNode) : ASTNode {
    override fun accept(visitor: ASTVisitor) {
        visitor.visit(this)
    }
}
