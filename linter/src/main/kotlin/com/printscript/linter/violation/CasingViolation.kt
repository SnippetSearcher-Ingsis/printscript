package com.printscript.linter.violation

import com.printscript.models.node.Position

data class CasingViolation(private val position: Position, private val caseType: String) : Violation {
  override fun toString(): String {
    return "Casing violation at $position, $caseType expected"
  }
}
