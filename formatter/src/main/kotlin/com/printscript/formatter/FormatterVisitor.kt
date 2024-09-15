package com.printscript.formatter

import com.printscript.models.node.ASTNode
import com.printscript.models.node.AssignationNode
import com.printscript.models.node.DeclarationNode
import com.printscript.models.node.DoubleExpressionNode
import com.printscript.models.node.ErrorNode
import com.printscript.models.node.IfElseNode
import com.printscript.models.node.LiteralNode
import com.printscript.models.node.PrintStatementNode
import com.printscript.models.node.ReadEnvNode
import com.printscript.models.node.ReadInputNode
import com.printscript.models.tool.Tool

class FormatterVisitor(private val config: FormatterConfig, private val outputCode: StringBuilder) : Tool {
  override fun evaluate(node: ASTNode) {
    visit(node)
  }

  fun visit(node: ASTNode) {
    val handler = Handler(config, outputCode, this)
    when (node) {
      is DoubleExpressionNode -> handler.handleDoubleExpression(node)
      is LiteralNode<*> -> handler.handleLiteral(node)
      is PrintStatementNode -> handler.handlePrintStatement(node)
      is DeclarationNode -> handler.handleDeclaration(node)
      is AssignationNode -> handler.handleAssignation(node)
      is ErrorNode -> {}
      is ReadEnvNode -> handler.handleReadEnv(node)
      is ReadInputNode -> handler.handleReadInput(node)
      is IfElseNode -> handler.handleIfElse(node)
      else -> throw IllegalArgumentException(node::class.simpleName ?: "Unknown")
    }
  }
}
