import node.ASTNode

class MyInterpreter : Interpreter {
    override fun interpret(nodes: List<ASTNode>) {
        val visitor = EvaluationVisitor()
        nodes.forEach { node ->
            node.accept(visitor)
        }
    }
}
