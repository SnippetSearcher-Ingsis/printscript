import exception.AssignationException
import exception.DeclarationException
import node.ASTNode

object Handler {
  infix fun print(node: ASTNode) {
    val value = Solver getValue node
    println(value)
  }

  fun declareValue(key: String, type: String, value: Any) {
    when {
      Context has key -> throw DeclarationException(key, type)
      value is Number && type.lowercase() != "number" -> throw DeclarationException(key, type)
      value is String && type.lowercase() != "string" -> throw DeclarationException(key, type)
      else -> Context.add(key, value)
    }
  }

  fun assignValue(key: String, value: Any) {
    println(key)
    println(value)
    println(Context get key)
    when {
      Context has key && (Context get key)!! hasSameTypeAs value -> Context.add(key, value)
      Context has key -> throw AssignationException(key)
      else -> throw AssignationException(key)
    }
  }

  private infix fun Any.hasSameTypeAs(b: Any): Boolean {
    return this::class == b::class
  }
}
