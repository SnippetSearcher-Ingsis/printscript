import node.ASTNode

class Interpreter : IInterpreter {
    override fun interpret(nodes: List<ASTNode>) {
        Context.clear()
        val visitor = EvaluationVisitor()
        nodes.forEach { node ->
            node.accept(visitor)
        }
    }
}
