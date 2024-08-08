package printScreen.models.ast

interface ExecutableAST {

    fun variable () : Pair<String, String>?
    fun type () : DataType
    fun node () : List<AST>

}