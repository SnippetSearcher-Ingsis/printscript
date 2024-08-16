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

    fun declareValue(key: String, value: Any) {
        when {
            Context has key -> println("Declaration error: $key is already defined.")
            else -> Context.add(key, value)
        }
    }

    fun assignValue(key: String, value: Any) {
        when {
            Context has key && areSameType((Context get key)!!, value) -> Context.add(key, value)
            Context get key != null -> println("Assignation error: $key is not ${value::class}.")
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
