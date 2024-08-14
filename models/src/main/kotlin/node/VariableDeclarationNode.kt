package node

class VariableDeclarationNode(
    val variable: String?,
    val variableType: String?,
    val execution: ASTNode
) : ASTNode {
    override fun accept(visitor: ASTVisitor) {
        visitor.visit(this)
    }

    override fun toString(): String {
        return "VariableDeclarationNode(variable=$variable, variableType=$variableType, execution=$execution)"
    }
}
