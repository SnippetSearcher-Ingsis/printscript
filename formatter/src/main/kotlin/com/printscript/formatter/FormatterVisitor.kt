package com.printscript.formatter

data class FormatterVisitor(private val config: FormatterConfig, private val outputCode: StringBuilder) :
  com.printscript.models.node.ASTVisitor,
  com.printscript.models.tool.Tool {
  override fun evaluate(node: com.printscript.models.node.ASTNode) {
    node.accept(this)
  }

  override fun visit(node: com.printscript.models.node.ErrorNode) {
  }

  override fun visit(node: com.printscript.models.node.DoubleExpressionNode) {
    handleExpression(node.left)
    append(" ${node.operator} ")
    handleExpression(node.right)
  }

  override fun visit(node: com.printscript.models.node.LiteralNode<*>) {
    append(node.value.toString())
  }

  override fun visit(node: com.printscript.models.node.PrintStatementNode) {
    append(config.lineBreaksBeforePrintsRule.apply())
    append("println(")
    evaluate(node.expression)
    append(")")
    endStatement()
  }

  override fun visit(node: com.printscript.models.node.VariableDeclarationNode) {
    append("let ${node.variable}")
    append(config.spaceAroundColonsRule.apply())
    append(node.variableType)
    append(config.spaceAroundEqualsRule.apply())
    evaluate(node.expression)
    endStatement()
  }

  override fun visit(node: com.printscript.models.node.AssignationNode) {
    append("${node.variable}")
    append(config.spaceAroundEqualsRule.apply())
    evaluate(node.expression)
    endStatement()
  }

  override fun visit(node: com.printscript.models.node.VariableNode) {
    append(node.name)
  }

  // utility functions
  private fun append(string: String) {
    outputCode.append(string)
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

  private fun handleExpression(node: com.printscript.models.node.ASTNode) {
    if (node is com.printscript.models.node.LiteralNode<*>) {
      node.accept(this)
    } else if (node is com.printscript.models.node.DoubleExpressionNode) {
      openExpression()
      node.accept(this)
      closeExpression()
    }
  }
}
