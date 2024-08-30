import node.ASTNode
import node.AssignationNode
import node.DoubleExpressionNode
import node.ErrorNode
import node.IfElseNode
import node.LiteralNode
import node.PrintStatementNode
import node.VariableDeclarationNode
import util.Context
import visitor.EvaluationStrategy
import visitor.Visitor

/**
 * Interpreter that evaluates the AST.
 */
class Interpreter : IInterpreter {
  private val context = Context()

  private val strategy = EvaluationStrategy()

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
}
