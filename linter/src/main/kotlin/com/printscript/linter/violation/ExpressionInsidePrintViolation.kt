package com.printscript.linter.violation

import com.printscript.models.node.Position

data class ExpressionInsidePrintViolation(private val position: Position) : Violation {
  override fun toString(): String {
    return "Expression inside print statement at $position"
  }
}
