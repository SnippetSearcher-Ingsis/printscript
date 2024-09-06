package com.printscript.interpreter.visitor

import com.printscript.interpreter.util.Context
import com.printscript.models.node.ASTVisitor
import com.printscript.models.node.AssignationNode
import com.printscript.models.node.DeclarationNode
import com.printscript.models.node.DoubleExpressionNode
import com.printscript.models.node.ErrorNode
import com.printscript.models.node.IfElseNode
import com.printscript.models.node.LiteralNode
import com.printscript.models.node.PrintStatementNode
import com.printscript.models.node.ReadEnvNode
import com.printscript.models.node.ReadInputNode

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

  override fun visit(node: DeclarationNode) {
    strategy.visit(context, node)
  }

  override fun visit(node: AssignationNode) {
    strategy.visit(context, node)
  }

  override fun visit(node: ErrorNode) {
    strategy.visit(context, node)
  }

  override fun visit(node: IfElseNode) {
    strategy.visit(context, node)
  }

  override fun visit(node: ReadEnvNode) {
    strategy.visit(context, node)
  }

  override fun visit(node: ReadInputNode) {
    strategy.visit(context, node)
  }
}
