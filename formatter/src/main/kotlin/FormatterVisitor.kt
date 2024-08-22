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