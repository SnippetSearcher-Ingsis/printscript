package violation

import node.Position

class ExpressionInsidePrintViolation(private val position: Position) : Violation {
    override fun toString(): String {
        return "Expression inside print statement at $position"
    }
}