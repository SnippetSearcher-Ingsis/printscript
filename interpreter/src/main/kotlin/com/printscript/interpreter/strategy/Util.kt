package com.printscript.interpreter.strategy

internal val validType = { value: Any, type: String ->
  when (type) {
    "number" -> value is Number
    "string" -> value is String
    "boolean" -> value is Boolean
    else -> false
  }
}
