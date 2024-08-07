package printScreen.models.ast

class Literal <X> (private val type : String, private val value: X) : Node {
    fun value () : X {
        return value
    }
    fun type () : String {
        return type
    }
}