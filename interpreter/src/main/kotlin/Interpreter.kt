import printScreen.models.ast.ast.AST

interface Interpreter {
    fun interpret(ast: List<AST>)
}
