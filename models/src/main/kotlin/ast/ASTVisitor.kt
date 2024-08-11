package ast

interface ASTVisitor<T> {
    fun visit(node: AST): T
}
