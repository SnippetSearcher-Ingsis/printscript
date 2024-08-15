import node.ASTNode

interface Interpreter {
    fun interpret(nodes: List<ASTNode>)
}
