package ast

import printScreen.models.ast.DoubleExpression
import printScreen.models.ast.Literal

interface ASTVisitor<T> {
    fun visit(node: Literal<T>): T
    fun visit(node: DoubleExpression): T
}
