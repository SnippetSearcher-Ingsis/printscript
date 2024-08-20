package errors

import java.lang.Error

class ReferenceError(value: String) : Error("Reference error: $value is not defined.")
