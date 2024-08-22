import node.ASTVisitor
import node.AssignationNode
import node.DoubleExpressionNode
import node.LiteralNode
import node.PrintStatementNode
import node.VariableDeclarationNode
import rule.RuleSet

class FormatterVisitor(private val ruleSet: RuleSet, private val outputCode: StringBuilder) : ASTVisitor {
  override fun visit(node: DoubleExpressionNode) {
    openExpression()
    node.left.accept(this)
    closeExpression()
    append(" ${node.operator} ")
    openExpression()
    node.right.accept(this)
    closeExpression()
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
}
