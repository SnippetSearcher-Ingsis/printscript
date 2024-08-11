package ast

class Literal<T>(val value: T) : AST {
    override fun <R> accept(visitor: ASTVisitor<R>):R {
        return visitor.visit(this)
    }
}
