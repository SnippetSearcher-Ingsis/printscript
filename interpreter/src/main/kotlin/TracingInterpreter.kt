import logger.ILog
import logger.Logger
import node.ASTNode
import node.AssignationNode
import node.DoubleExpressionNode
import node.ErrorNode
import node.IfElseNode
import node.LiteralNode
import node.PrintStatementNode
import node.VariableDeclarationNode
import util.Context
import visitor.TracingStrategy
import visitor.Visitor

/**
 * Interpreter that logs the execution of the program.
 * @param print If true, the interpreter will print the execution of the program to the standard output.
 */
class TracingInterpreter(private val print: Boolean = true) : IInterpreter, ILog {
  private val logger = Logger()

  private val context = Context()

  private val strategy = TracingStrategy(logger, print = print)

  private val visitor = Visitor(context, strategy)

  override fun interpret(nodes: Iterator<ASTNode>) {
    while (nodes.hasNext()) {
      when (val node = nodes.next()) {
        is AssignationNode -> visitor.visit(node)
        is DoubleExpressionNode -> visitor.visit(node)
        is LiteralNode<*> -> visitor.visit(node)
        is PrintStatementNode -> visitor.visit(node)
        is VariableDeclarationNode -> visitor.visit(node)
        is IfElseNode -> visitor.visit(node)
        is ErrorNode -> throw IllegalArgumentException(node.error)
        else -> throw IllegalArgumentException("Unknown node type")
      }
    }
  }

  override fun getLog(): List<String> = logger.getLog()
}
