package com.printscript.formatter

import com.printscript.models.node.ASTNode
import com.printscript.models.node.AssignationNode
import com.printscript.models.node.ConstantDeclarationNode
import com.printscript.models.node.ConstantNode
import com.printscript.models.node.DeclarationNode
import com.printscript.models.node.DoubleExpressionNode
import com.printscript.models.node.IfElseNode
import com.printscript.models.node.LineCommentNode
import com.printscript.models.node.LiteralNode
import com.printscript.models.node.PrintStatementNode
import com.printscript.models.node.ReadEnvNode
import com.printscript.models.node.ReadInputNode
import com.printscript.models.node.VariableDeclarationNode
import com.printscript.models.node.VariableNode
import com.printscript.models.tool.Tool

class Handler(
  private val config: FormatterConfig,
  private val outputCode: StringBuilder,
  private val visitor: FormatterVisitor
) : Tool {
  override fun evaluate(node: ASTNode) {
    visitor.visit(node)
  }

  fun handleDoubleExpression(node: DoubleExpressionNode) {
    handleExpression(node.left, visitor, outputCode)
    append(" ${node.operator} ", outputCode)
    handleExpression(node.right, visitor, outputCode)
  }

  fun handleLiteral(node: LiteralNode<*>) {
    append(node.value.toString(), outputCode)
  }

  fun handlePrintStatement(node: PrintStatementNode) {
    append("println(", outputCode)
    evaluate(node.expression)
    append(")", outputCode)
    endStatement(outputCode)
    append(config.lineBreaksAfterPrintsRule.apply(), outputCode)
  }

  fun handleDeclaration(node: DeclarationNode) {
    append(
      when (node) {
        is ConstantDeclarationNode -> "const "
        is ConstantNode -> "const "
        is VariableDeclarationNode -> "let "
        is VariableNode -> "let "
      }, outputCode
    )
    append(node.identifier, outputCode)
    append(config.spaceAroundColonsRule.apply(), outputCode)
    append(node.valueType, outputCode)
    append(config.spaceAroundEqualsRule.apply(), outputCode)
    evaluate(node.expression)
    endStatement(outputCode)
  }

  fun handleAssignation(node: AssignationNode) {
    append("${node.variable}", outputCode)
    append(config.spaceAroundEqualsRule.apply(), outputCode)
    evaluate(node.expression)
    endStatement(outputCode)
  }

  fun handleReadEnv(node: ReadEnvNode) {
    append("readEnv(", outputCode)
    evaluate(node.expression)
    append(")", outputCode)
    endStatement(outputCode)
  }

  fun handleReadInput(node: ReadInputNode) {
    append("readInput(", outputCode)
    evaluate(node.expression)
    append(")", outputCode)
    endStatement(outputCode)
  }

  fun handleIfElse(node: IfElseNode) {
    append("if (", outputCode)
    evaluate(node.condition)
    append(")", outputCode)
    append(config.inlineIfBraceRule.apply(), outputCode)
    node.ifBranch.forEach { indent(outputCode, config); evaluate(it) }
    append("}\n", outputCode)
    if (node.elseBranch.isEmpty()) return
    append("else", outputCode)
    append(config.inlineIfBraceRule.apply(), outputCode)
    node.elseBranch.forEach { indent(outputCode, config); evaluate(it) }
    append("}\n", outputCode)
  }

  fun handleComment(node: LineCommentNode) {
    append(node.comment, outputCode)
    append("\n", outputCode)
  }
}

private fun append(string: String, outputCode: StringBuilder) {
  outputCode.append(string)
}

private fun indent(outputCode: StringBuilder, config: FormatterConfig) {
  outputCode.append(config.indentRule.apply())
}

private fun endStatement(outputCode: StringBuilder) {
  outputCode.append(";\n")
}

private fun openExpression(outputCode: StringBuilder) {
  outputCode.append("(")
}

private fun closeExpression(outputCode: StringBuilder) {
  outputCode.append(")")
}

private fun handleExpression(node: ASTNode, visitor: FormatterVisitor, outputCode: StringBuilder) {
  if (node is LiteralNode<*>) {
    visitor.visit(node)
  } else if (node is DoubleExpressionNode) {
    openExpression(outputCode)
    visitor.visit(node)
    closeExpression(outputCode)
  }
}
