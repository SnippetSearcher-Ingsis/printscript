package ast

class DoubleExpression(val operator: String, val left: AST, val right: AST) : AST {
    override fun <T> accept(visitor: ASTVisitor<T>): T {
        return visitor.visit(this)
    }
}
