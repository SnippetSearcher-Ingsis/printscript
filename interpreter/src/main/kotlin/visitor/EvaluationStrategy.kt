package visitor

import node.AssignationNode
import node.DoubleExpressionNode
import node.ErrorNode
import node.IfElseNode
import node.LiteralNode
import node.PrintStatementNode
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

  override fun visit(context: Context, node: IfElseNode) {
    val boolean = node.condition.value.toString().toBoolean()
    val internalContext = Context()
    context.forEach { internalContext.put(it.key, it.value) }
    val branch = if (boolean) node.ifBranch else node.elseBranch
    branch.forEach { it.accept(Visitor(internalContext, this)) }
    internalContext.forEach { if (context has it.key) context.put(it.key, it.value) }
  }
}
