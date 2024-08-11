import ast.AST

class MyInterpreter : Interpreter {
    override fun interpret(ast: List<AST>) {
        val visitor = EvaluationVisitor()
        ast.forEach { node ->
            node.accept(visitor)
        }
    }
}
