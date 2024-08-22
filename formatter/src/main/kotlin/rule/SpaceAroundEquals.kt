package rule

import node.ASTNode
import node.AssignationNode
import node.VariableDeclarationNode

class SpaceAroundEquals(val active: Boolean) : Rule {
    fun apply() : String {
        val result = StringBuilder()
        if (active) {
            result.append(" ")
        }
        result.append("=")
        if (active) {
            result.append(" ")
        }
        return result.toString()
    }
}