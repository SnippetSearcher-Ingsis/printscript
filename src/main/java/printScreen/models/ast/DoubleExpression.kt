package printScreen.models.ast

class DoubleExpression (private val left: Node, private val operator: String, private val right: Node) : Node {

    fun left () : Node {
        return left
    }

    fun right () : Node {
        return right
    }

    fun operator () : String {
        return operator
    }

}
