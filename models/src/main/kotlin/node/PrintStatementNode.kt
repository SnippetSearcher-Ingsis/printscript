package node

class PrintStatementNode(val expression: ASTNode) : ASTNode {
    override fun accept(visitor: ASTVisitor) {
        visitor.visit(this)
    }

    override fun toString(): String {
        return "PrintStatementNode(expression=$expression)"
    }
}
