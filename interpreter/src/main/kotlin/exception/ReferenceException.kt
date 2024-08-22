package exception

class ReferenceException(value: String) : Exception("Reference error: $value is not defined.")
