import node.ASTNode
import node.ASTVisitor
import node.AssignationNode
import node.DoubleExpressionNode
import node.LiteralNode
import node.PrintStatementNode
import node.VariableDeclarationNode
import rule.RuleSet

class FormatterVisitor(private val ruleSet: RuleSet, private val outputCode: StringBuilder) : ASTVisitor {
  override fun visit(node: DoubleExpressionNode) {
    handleExpression(node.left)
    append(" ${node.operator} ")
    handleExpression(node.right)
  }

  override fun visit(node: LiteralNode<*>) {
    append(node.value.toString())
  }

  override fun visit(node: PrintStatementNode) {
    append("\n".repeat(ruleSet.lineBreaksBeforePrints.lineBreaks))
    append("println")
    openExpression()
    node.expression.accept(this)
    closeExpression()
    appendNewLine()
  }

  override fun visit(node: VariableDeclarationNode) {
    append("let ${node.variable}")
    append(ruleSet.spaceAroundColons.apply())
    append(node.variableType)
    append(ruleSet.spaceAroundEquals.apply())
    node.expression.accept(this)
    appendNewLine()
  }
  override fun visit(node: AssignationNode) {
    append("${node.variable}")
    append(ruleSet.spaceAroundEquals.apply())
    node.expression.accept(this)
    appendNewLine()
  }

  private fun append(string: String) {
    outputCode.append(string)
  }

  private fun appendNewLine() {
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
      if (node.operator == "+" || node.operator == "-") {
        node.left.accept(this)
      }
      if (node.operator == "-") {
        openExpression()
        node.left.accept(this)
        closeExpression()
      }

      openExpression()
      node.accept(this)
      closeExpression()
    }
  }
}
