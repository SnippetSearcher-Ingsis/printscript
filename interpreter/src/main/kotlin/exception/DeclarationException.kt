package exception

class DeclarationException(private val name: String, private val type: String) :
  Exception("Declaration error: $name is not $type.")
