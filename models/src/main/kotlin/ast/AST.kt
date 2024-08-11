package ast

interface AST {
    fun <T> accept(visitor: ASTVisitor<T>): T
}
