package ast

class ExecutionLine (val variable : String? , val variableType : String? , val dataType : DataType, val execution : AST )  : AST {
    override fun accept(visitor: ASTVisitor): AST {
        TODO("Not yet implemented")
    }
}