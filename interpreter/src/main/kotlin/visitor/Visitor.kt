package visitor

import node.ASTVisitor
import node.AssignationNode
import node.DoubleExpressionNode
import node.ErrorNode
import node.LiteralNode
import node.PrintStatementNode
import node.VariableDeclarationNode
import util.Context

internal class Visitor(private val context: Context, private val strategy: VisitorStrategy) : ASTVisitor {
  override fun visit(node: DoubleExpressionNode) {
    strategy.visit(context, node)
  }

  override fun visit(node: LiteralNode<*>) {
    strategy.visit(context, node)
  }

  override fun visit(node: PrintStatementNode) {
    strategy.visit(context, node)
  }

  override fun visit(node: VariableDeclarationNode) {
    strategy.visit(context, node)
  }

  override fun visit(node: AssignationNode) {
    strategy.visit(context, node)
  }

  override fun visit(node: ErrorNode) {
    strategy.visit(context, node)
  }
}
