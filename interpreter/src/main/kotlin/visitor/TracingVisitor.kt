package visitor

import Solver
import logger.ILogger
import node.ASTVisitor
import node.AssignationNode
import node.DoubleExpressionNode
import node.LiteralNode
import node.PrintStatementNode
import node.VariableDeclarationNode

class TracingVisitor(private val visitor: ASTVisitor, private val logger: ILogger) : ASTVisitor {
  override fun visit(node: DoubleExpressionNode) {
    visitor.visit(node)
  }

  override fun visit(node: LiteralNode<*>) {
    visitor.visit(node)
  }

  override fun visit(node: PrintStatementNode) {
    val result = Solver getValue node.expression
    logger.log(result.toString())
    visitor.visit(node)
  }

  override fun visit(node: VariableDeclarationNode) {
    visitor.visit(node)
  }

  override fun visit(node: AssignationNode) {
    visitor.visit(node)
  }
}
