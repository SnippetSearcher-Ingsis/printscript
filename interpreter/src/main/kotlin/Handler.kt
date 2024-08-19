import node.ASTNode

object Handler {
    infix fun print(node: ASTNode) {
        val value = Solver getValue node
        when {
            value is String && !value.startsWith("\"") -> {
                println((Context get value) ?: "Reference error: $value is not defined.")
            }

            else -> println(value)
        }
    }

    fun declareValue(key: String, type: String, value: Any) {
        when {
            Context has key -> println("Declaration error: $key is already defined.")
            value is Number && type.lowercase() != "number" -> println("Declaration error: $key is not $type.")
            value is String && type.lowercase() != "string" -> println("Declaration error: $key is not $type.")
            else -> Context.add(key, value)
        }
    }

    fun assignValue(key: String, value: Any) {
        when {
            Context has key && areSameType((Context get key)!!, value) -> Context.add(key, value)
            Context has key -> println("Assignation error: $key is not ${value::class}.")
            else -> println("Assignation error: $key is not defined.")
        }
    }

    private fun areSameType(a: Any, b: Any): Boolean {
        return when {
            a is Number && b is Number -> true
            a is String && b is String -> true
            else -> false
        }
    }
}
