<<<<<<< HEAD
import node.ASTVisitor
import node.AssignationNode
import node.DoubleExpressionNode
import node.LiteralNode
import node.PrintStatementNode
import node.VariableDeclarationNode
import rule.Rule

class FormatterVisitor(private val rules: List<Rule>, private val outputCode: StringBuilder) :
  ASTVisitor {
  override fun visit(node: DoubleExpressionNode) {
  }

  override fun visit(node: LiteralNode<*>) {
    TODO("Not yet implemented")
  }

  override fun visit(node: PrintStatementNode) {
    TODO("Not yet implemented")
  }

  override fun visit(node: VariableDeclarationNode) {
    TODO("Not yet implemented")
  }

  override fun visit(node: AssignationNode) {
    TODO("Not yet implemented")
  }
}
=======
import node.*
import rule.RuleSet

class FormatterVisitor(private val ruleSet: RuleSet, private val outputCode: StringBuilder) : ASTVisitor{
    override fun visit(node: DoubleExpressionNode) {
        node.left.accept(this)
        append(" ${node.operator} ")
        node.right.accept(this)
    }

    override fun visit(node: LiteralNode<*>) {
        append(node.value.toString())
    }

    override fun visit(node: PrintStatementNode) {
        append("\n".repeat(ruleSet.lineBreaksBeforePrints.lineBreaks))
        append("println(")
        node.expression.accept(this)
        append(")")
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
    private fun appendWhitespace() {
        outputCode.append(" ")
    }
    private fun appendNewLine() {
        outputCode.append(";\n")
    }
}
>>>>>>> 736857229d1a6447f2d70635b2c775520d322df5
