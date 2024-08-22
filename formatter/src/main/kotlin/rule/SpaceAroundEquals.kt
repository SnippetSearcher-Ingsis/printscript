package rule

<<<<<<< HEAD
class SpaceAroundEquals(val active: Boolean) : Rule
=======
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
>>>>>>> 736857229d1a6447f2d70635b2c775520d322df5
