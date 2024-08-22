package errors

class OperationError(private val operation: String) :
  Error("Operator error: $operation is not supported.")
