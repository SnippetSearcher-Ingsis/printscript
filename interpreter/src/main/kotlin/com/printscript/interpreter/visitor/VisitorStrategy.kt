package com.printscript.interpreter.visitor

import com.printscript.interpreter.util.Context
import com.printscript.models.node.AssignationNode
import com.printscript.models.node.DeclarationNode
import com.printscript.models.node.DoubleExpressionNode
import com.printscript.models.node.ErrorNode
import com.printscript.models.node.IfElseNode
import com.printscript.models.node.LiteralNode
import com.printscript.models.node.PrintStatementNode
import com.printscript.models.node.ReadEnvNode
import com.printscript.models.node.ReadInputNode

internal interface VisitorStrategy {
  fun visit(context: Context, node: DoubleExpressionNode)

  fun visit(context: Context, node: LiteralNode<*>)

  fun visit(context: Context, node: PrintStatementNode)

  fun visit(context: Context, node: DeclarationNode)

  fun visit(context: Context, node: AssignationNode)

  fun visit(context: Context, node: ErrorNode)

  fun visit(context: Context, node: IfElseNode)

  fun visit(context: Context, node: ReadEnvNode)

  fun visit(context: Context, node: ReadInputNode)
}
