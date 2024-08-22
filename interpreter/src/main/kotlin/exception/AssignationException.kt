package exception

class AssignationException(private val variable: String) :
  Exception("Assignation error: $variable is not defined.")
