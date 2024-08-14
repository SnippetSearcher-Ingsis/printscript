import node.ASTNode

class MyInterpreter : Interpreter {
    override fun interpret(astNode: List<ASTNode>) {
        val visitor = EvaluationVisitor()
        astNode.forEach { node ->
            node.accept(visitor)
        }
    }
}
