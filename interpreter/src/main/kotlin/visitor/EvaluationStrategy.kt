package visitor

import node.AssignationNode
import node.ConstantDeclarationNode
import node.DoubleExpressionNode
import node.ErrorNode
import node.IfElseNode
import node.LiteralNode
import node.PrintStatementNode
import node.ReadEnvNode
import node.ReadInputNode
import node.VariableDeclarationNode
import util.Context
import util.Handler
import util.Solver

internal object EvaluationStrategy : VisitorStrategy {
  override fun visit(context: Context, node: DoubleExpressionNode) {
    Solver.getValue(context, node)
  }

  override fun visit(context: Context, node: LiteralNode<*>) {}

  override fun visit(context: Context, node: PrintStatementNode) {
    Handler.print(context, node.expression)
  }

  override fun visit(context: Context, node: VariableDeclarationNode) {
    Handler.declareValue(context, node)
  }

  override fun visit(context: Context, node: AssignationNode) {
    Handler.assignValue(context, node)
  }

  override fun visit(context: Context, node: ErrorNode) {
  }

  override fun visit(context: Context, node: ReadEnvNode) {
  }

  override fun visit(context: Context, node: ReadInputNode) {
  }

  override fun visit(context: Context, node: ConstantDeclarationNode) {
    Handler.declareValue(context, node)
  }

  override fun visit(context: Context, node: IfElseNode) {
    Handler.runBranch(context, node)
  }
}
