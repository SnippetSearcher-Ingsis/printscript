package ast

class Literal<T>(val value: T) : AST {

    override fun accept(visitor: ASTVisitor): AST {
        return visitor.visit(this)
    }
}
