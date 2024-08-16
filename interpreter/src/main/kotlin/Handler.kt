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
            Context has key && (Context get key)!!::class == value::class -> Context.add(key, value)
            Context get key != null -> println("Assignation error: $key is not ${value::class}.")
            else -> println("Assignation error: $key is not defined.")
        }
    }
}
