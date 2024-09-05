import node.ASTNode
import node.ASTVisitor
import node.AssignationNode
import node.ConstantDeclarationNode
import node.DeclarationNode
import node.DoubleExpressionNode
import node.ErrorNode
import node.IfElseNode
import node.LiteralNode
import node.PrintStatementNode
import node.ReadEnvNode
import node.ReadInputNode
import tool.Tool

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
    node.ifBranch.forEach { evaluate(it) }
    append("} \n")
    if (node.elseBranch.isEmpty()) return
    append("else { \n")
    node.elseBranch.forEach { evaluate(it) }
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
    append("readInput(${node.value})")
  }

  override fun visit(node: ReadEnvNode) {
    append("readEnv(${node.value})")
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
