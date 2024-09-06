package com.printscript.formatter

import com.printscript.models.node.ASTNode
import com.printscript.models.node.ASTVisitor
import com.printscript.models.node.AssignationNode
import com.printscript.models.node.ConstantDeclarationNode
import com.printscript.models.node.DeclarationNode
import com.printscript.models.node.DoubleExpressionNode
import com.printscript.models.node.ErrorNode
import com.printscript.models.node.IfElseNode
import com.printscript.models.node.LiteralNode
import com.printscript.models.node.PrintStatementNode
import com.printscript.models.node.ReadEnvNode
import com.printscript.models.node.ReadInputNode
import com.printscript.models.tool.Tool

data class FormatterVisitor(private val config: FormatterConfig, private val outputCode: StringBuilder) :
  ASTVisitor,
  Tool {
  override fun evaluate(node: ASTNode) {
    node.accept(this)
  }

  override fun visit(node: ErrorNode) {
  }

  override fun visit(node: IfElseNode) {
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

  override fun visit(node: DoubleExpressionNode) {
    handleExpression(node.left)
    append(" ${node.operator} ")
    handleExpression(node.right)
  }

  override fun visit(node: LiteralNode<*>) {
    append(node.value.toString())
  }

  override fun visit(node: PrintStatementNode) {
    append(config.lineBreaksBeforePrintsRule.apply())
    append("println(")
    evaluate(node.expression)
    append(")")
    endStatement()
  }

  override fun visit(node: DeclarationNode) {
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

  override fun visit(node: AssignationNode) {
    append("${node.variable}")
    append(config.spaceAroundEqualsRule.apply())
    evaluate(node.expression)
    endStatement()
  }

  override fun visit(node: ReadInputNode) {
    append("readInput(${node.expression})")
  }

  override fun visit(node: ReadEnvNode) {
    append("readEnv(${node.value})")
  }

  // utility functions
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
      node.accept(this)
    } else if (node is DoubleExpressionNode) {
      openExpression()
      node.accept(this)
      closeExpression()
    }
  }
}
