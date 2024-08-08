package interpreter

import printScreen.models.ast.AST

interface Interpreter {
    fun interpret(ast: List<AST>)
}
