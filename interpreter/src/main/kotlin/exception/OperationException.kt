package exception

class OperationException(private val operation: String) :
  Exception("Operator error: $operation is not supported.")
