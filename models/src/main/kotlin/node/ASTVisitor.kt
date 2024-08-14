package node

interface ASTVisitor {
    fun visit(node: ASTNode): ASTNode
}
