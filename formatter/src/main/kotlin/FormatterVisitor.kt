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
    append(ruleSet.lineBreaksBeforePrints.apply())
    append("println(")
    node.expression.accept(this)
    append(")")
    endStatement()
  }

  override fun visit(node: VariableDeclarationNode) {
    append("let ${node.variable}")
    append(ruleSet.spaceAroundColons.apply())
    append(node.variableType)
    append(ruleSet.spaceAroundEquals.apply())
    node.expression.accept(this)
    endStatement()
  }
  override fun visit(node: AssignationNode) {
    append("${node.variable}")
    append(ruleSet.spaceAroundEquals.apply())
    node.expression.accept(this)
    endStatement()
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
