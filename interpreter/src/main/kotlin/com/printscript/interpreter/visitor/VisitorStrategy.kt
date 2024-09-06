package com.printscript.interpreter.visitor

import com.printscript.interpreter.util.Context

internal sealed interface VisitorStrategy {
  fun visit(context: Context, node: com.printscript.models.node.DoubleExpressionNode)

  fun visit(context: Context, node: com.printscript.models.node.LiteralNode<*>)

  fun visit(context: Context, node: com.printscript.models.node.PrintStatementNode)

  fun visit(context: Context, node: com.printscript.models.node.VariableDeclarationNode)

  fun visit(context: Context, node: com.printscript.models.node.AssignationNode)

  fun visit(context: Context, node: com.printscript.models.node.ErrorNode)

  fun visit(context: Context, node: com.printscript.models.node.VariableNode)
}
