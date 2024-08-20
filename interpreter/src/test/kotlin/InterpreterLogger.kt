import logger.ILogger
import node.ASTNode

class InterpreterLogger : IInterpreter, ILogger {

    private val visitor = DummyVisitor(EvaluationVisitor(), this)

    private val log = mutableListOf<String>()

    override fun interpret(nodes: List<ASTNode>) {
        Context.clear()
        nodes.forEach { it.accept(visitor) }
    }

    override fun getLog(): List<String> {
        return log
    }

    override fun log(message: String) {
        log.add(message)
    }
}
