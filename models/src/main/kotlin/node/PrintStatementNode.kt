package node

class PrintStatementNode(val expression: ASTNode, val position: Position) : ASTNode {
    override fun accept(visitor: ASTVisitor) {
        visitor.visit(this)
    }

    override fun toString(): String {
        return "PrintStatementNode(expression=$expression)"
    }
}
