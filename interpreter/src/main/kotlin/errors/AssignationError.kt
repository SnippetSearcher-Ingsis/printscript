package errors

class AssignationError(private val variable: String) :
    Error("Assignation error: $variable is not defined.")
