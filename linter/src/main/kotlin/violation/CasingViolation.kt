package violation

import node.Position

data class CasingViolation(private val position: Position, private val caseType: String) : Violation {
  override fun toString(): String {
    return "Casing violation at $position, $caseType case expected"
  }
}
