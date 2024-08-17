import node.ASTNode

interface IInterpreter {
    infix fun interpret(nodes: List<ASTNode>)
}
