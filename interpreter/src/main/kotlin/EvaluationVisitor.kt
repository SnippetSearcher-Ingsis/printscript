import node.ASTVisitor
import node.AssignationNode
import node.DoubleExpressionNode
import node.LiteralNode
import node.PrintStatementNode
import node.VariableDeclarationNode

class EvaluationVisitor : ASTVisitor {
  override fun visit(node: DoubleExpressionNode) {
    Solver getValue node
  }

  override fun visit(node: LiteralNode<*>) {}

  override fun visit(node: PrintStatementNode) {
    Handler print node.expression
  }

  override fun visit(node: VariableDeclarationNode) {
    val value = Solver getValue node.expression
    Handler.declareValue(node.variable, node.variableType, value)
  }

  override fun visit(node: AssignationNode) {
    val value = Solver getValue node.expression
    Handler.assignValue(node.variable!!, value)
  }
}
