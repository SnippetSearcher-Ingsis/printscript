package logger

import Context
import EvaluationVisitor
import IInterpreter
import node.ASTNode

class TracingInterpreter : IInterpreter, ILog {
    private val logger = Logger()

    private val visitor = TracingVisitor(EvaluationVisitor(), logger)

    override fun interpret(nodes: List<ASTNode>) {
        Context.clear()
        nodes.forEach { it.accept(visitor) }
    }

    override fun getLog(): List<String> = logger.getLog()
}
