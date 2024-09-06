package com.printscript.interpreter.visitor

import com.printscript.interpreter.util.Context

internal class Visitor(private val context: Context, private val strategy: VisitorStrategy) :
  com.printscript.models.node.ASTVisitor {
  override fun visit(node: com.printscript.models.node.DoubleExpressionNode) {
    strategy.visit(context, node)
  }

  override fun visit(node: com.printscript.models.node.LiteralNode<*>) {
    strategy.visit(context, node)
  }

  override fun visit(node: com.printscript.models.node.PrintStatementNode) {
    strategy.visit(context, node)
  }

  override fun visit(node: com.printscript.models.node.VariableDeclarationNode) {
    strategy.visit(context, node)
  }

  override fun visit(node: com.printscript.models.node.AssignationNode) {
    strategy.visit(context, node)
  }

  override fun visit(node: com.printscript.models.node.ErrorNode) {
    strategy.visit(context, node)
  }

  override fun visit(node: com.printscript.models.node.VariableNode) {
    strategy.visit(context, node)
  }
}
