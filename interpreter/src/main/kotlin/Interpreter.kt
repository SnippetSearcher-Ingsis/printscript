import node.ASTNode

interface Interpreter {
    fun interpret(astNode: List<ASTNode>)
}
