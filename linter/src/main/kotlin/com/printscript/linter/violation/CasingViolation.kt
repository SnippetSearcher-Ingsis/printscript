package com.printscript.linter.violation

data class CasingViolation(private val position: com.printscript.models.node.Position, private val caseType: String) : Violation {
  override fun toString(): String {
    return "Casing violation at $position, $caseType expected"
  }
}
