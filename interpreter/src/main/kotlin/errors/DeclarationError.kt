package errors

class DeclarationError(private val name: String, private val type: String) :
    Error("Declaration error: $name is not $type.")
