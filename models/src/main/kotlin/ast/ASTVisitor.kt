package ast

interface ASTVisitor {
    fun visit(node: AST): AST
}
