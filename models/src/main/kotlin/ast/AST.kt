package ast

interface AST {
    fun accept(visitor: ASTVisitor): AST
}
