package com.printscript.linter.violation

data class ExpressionInsidePrintViolation(private val position: com.printscript.models.node.Position) : Violation {
  override fun toString(): String {
    return "Expression inside print statement at $position"
  }
}
