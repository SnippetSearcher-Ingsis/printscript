package visitor

import Context
import Handler
import Solver
import node.ASTVisitor
import node.AssignationNode
import node.DoubleExpressionNode
import node.LiteralNode
import node.PrintStatementNode
import node.VariableDeclarationNode

internal class EvaluationVisitor(private val context: Context) : ASTVisitor {
  override fun visit(node: DoubleExpressionNode) {
    Solver.getValue(context, node)
  }

  override fun visit(node: LiteralNode<*>) {}

  override fun visit(node: PrintStatementNode) {
    Handler.print(context, node.expression)
  }

  override fun visit(node: VariableDeclarationNode) {
    Handler.declareValue(context, node)
  }

  override fun visit(node: AssignationNode) {
    Handler.assignValue(context, node)
  }
}
