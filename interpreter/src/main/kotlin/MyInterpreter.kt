import node.ASTNode
import node.LiteralNode
import node.PrintStatementNode
import node.VariableDeclarationNode

class MyInterpreter : Interpreter {
    override fun interpret(nodes: List<ASTNode>) {
        Context.clear()
        val visitor = EvaluationVisitor()
        nodes.forEach { node ->
            node.accept(visitor)
        }
    }
}
