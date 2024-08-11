package printScreen.models.ast

class AST ( private val variable : Pair<String, String?>? , private val type : DataType , private val node : Node) : ExecutableAST {

    override fun variable () : Pair<String, String?>? {
        return variable
    }

    override fun type () : DataType {
        return type
    }

    override fun node () : Node {
        return node
    }
}
    //nombre para casos de variables, type para saber si es una variable o mas tarde loop
    // o condicional y por ultimo los arboles de ejecución


