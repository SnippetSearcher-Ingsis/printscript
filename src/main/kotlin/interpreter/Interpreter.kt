package interpreter

import ast.AST

interface Interpreter {
    fun interpret(ast: List<AST>)
}
