import errors.AssignationError
import errors.DeclarationError
import node.ASTNode

object Handler {
    infix fun print(node: ASTNode) {
        val value = Solver getValue node
        println(value)
    }

    fun declareValue(key: String, type: String, value: Any) {
        when {
            Context has key -> throw DeclarationError(key, type)
            value is Number && type.lowercase() != "number" -> throw DeclarationError(key, type)
            value is String && type.lowercase() != "string" -> throw DeclarationError(key, type)
            else -> Context.add(key, value)
        }
    }

    fun assignValue(key: String, value: Any) {
        println(key)
        println(value)
        println(Context get key)
        when {
            Context has key && areSameType((Context get key)!!, value) -> Context.add(key, value)
            Context has key -> throw AssignationError(key)
            else -> throw AssignationError(key)
        }
    }

    private fun areSameType(a: Any, b: Any): Boolean = a::class == b::class
}
