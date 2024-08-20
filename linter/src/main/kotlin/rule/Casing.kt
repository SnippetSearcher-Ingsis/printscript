package rule

import node.ASTNode
import node.VariableDeclarationNode
import violation.CasingViolation
import violation.Violation

class Casing(private val caseType: String) : Rule {
    override fun check(node: ASTNode): Violation? {
        if (node !is VariableDeclarationNode) {
            return null
        }
        return check(node)
    }

    private fun check(node: VariableDeclarationNode): CasingViolation? {
        var violation = false
        when (caseType) {
            "camel" -> {
                if (!isCamelCase(node.variable)) {
                    violation = true
                }
            }

            "snake" -> {
                if (!isSnakeCase(node.variable)) {
                    violation = true
                }
            }

            else -> {
                throw Exception("Case type $caseType is not supported")
            }
        }
        return if (violation) {
            CasingViolation(node.position, caseType)
        } else {
            null
        }
    }

    private fun isCamelCase(input: String): Boolean {
        return input[0].isLowerCase() && !input.contains("_")
    }

    private fun isSnakeCase(input: String): Boolean {
        for (c in input) {
            if (c.isUpperCase()) {
                return false
            }
        }
        return true
    }
}