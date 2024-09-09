package com.printscript.formatter

import com.printscript.models.node.ASTNode
import com.printscript.models.node.AssignationNode
import com.printscript.models.node.ConstantDeclarationNode
import com.printscript.models.node.DeclarationNode
import com.printscript.models.node.DoubleExpressionNode
import com.printscript.models.node.IfElseNode
import com.printscript.models.node.LiteralNode
import com.printscript.models.node.PrintStatementNode
import com.printscript.models.node.ReadEnvNode
import com.printscript.models.node.ReadInputNode
import com.printscript.models.tool.Tool

class Handler(private val config: FormatterConfig, private val outputCode: StringBuilder, private val visitor: FormatterVisitor) : Tool {
  override fun evaluate(node: ASTNode) {
    visitor.visit(node)
  }

  fun handleDoubleExpression(node: DoubleExpressionNode) {
    handleExpression(node.left)
    append(" ${node.operator} ")
    handleExpression(node.right)
  }

  fun handleLiteral(node: LiteralNode<*>) {
    append(node.value.toString())
  }

  fun handlePrintStatement(node: PrintStatementNode) {
    append(config.lineBreaksBeforePrintsRule.apply())
    append("println(")
    evaluate(node.expression)
    append(")")
    endStatement()
  }

  fun handleDeclaration(node: DeclarationNode) {
    if (node is ConstantDeclarationNode) {
      append("const ${node.variable}")
    } else {
      append("let ${node.variable}")
    }
    append(config.spaceAroundColonsRule.apply())
    append(node.variableType)
    append(config.spaceAroundEqualsRule.apply())
    evaluate(node.expression)
    endStatement()
  }

  fun handleAssignation(node: AssignationNode) {
    append("${node.variable}")
    append(config.spaceAroundEqualsRule.apply())
    evaluate(node.expression)
    endStatement()
  }

  fun handleReadEnv(node: ReadEnvNode) {
    append("readEnv(")
    evaluate(node.expression)
    append(")")
  }

  fun handleReadInput(node: ReadInputNode) {
    append("readInput(")
    evaluate(node.expression)
    append(")")
  }

  fun handleBranch(node: IfElseNode) {
    append("if (")
    evaluate(node.condition)
    append(") { \n")
    node.ifBranch.forEach { indent(); evaluate(it) }
    append("} \n")
    if (node.elseBranch.isEmpty()) return
    append("else { \n")
    node.elseBranch.forEach { indent(); evaluate(it) }
    append("} \n")
  }

  private fun append(string: String) {
    outputCode.append(string)
  }

  private fun indent() {
    outputCode.append(config.indentRule.apply())
  }

  private fun endStatement() {
    outputCode.append(";\n")
  }

  private fun openExpression() {
    outputCode.append("(")
  }

  private fun closeExpression() {
    outputCode.append(")")
  }

  private fun handleExpression(node: ASTNode) {
    if (node is LiteralNode<*>) {
      visitor.visit(node)
    } else if (node is DoubleExpressionNode) {
      openExpression()
      visitor.visit(node)
      closeExpression()
    }
  }
}
