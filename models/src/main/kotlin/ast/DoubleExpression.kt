package ast

class DoubleExpression(val operator: String, val left: AST, val right: AST) : AST {

    override fun accept(visitor: ASTVisitor): AST {
        return visitor.visit(this)
    }
}
