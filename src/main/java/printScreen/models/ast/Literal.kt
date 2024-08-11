package printScreen.models.ast

class Literal <X> (private val value: X?) : Node {

    fun value () : X? {
        return value
    }

}