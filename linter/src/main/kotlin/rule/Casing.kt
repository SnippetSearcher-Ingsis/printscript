package rule

import node.ASTNode
import node.VariableDeclarationNode
import rule.caseTypes.CamelCase
import rule.caseTypes.Case
import rule.caseTypes.KebabCase
import rule.caseTypes.PascalCase
import rule.caseTypes.ScreamingKebabCase
import rule.caseTypes.ScreamingSnakeCase
import rule.caseTypes.SnakeCase
import violation.CasingViolation

data class Casing(private val caseType: String) : Rule {
  private val case: Case = when (caseType) {
    "camel" -> CamelCase
    "pascal" -> PascalCase
    "snake" -> SnakeCase
    "screaming_snake" -> ScreamingSnakeCase
    "kebab" -> KebabCase
    "screaming_kebab" -> ScreamingKebabCase
    else -> throw IllegalArgumentException("Case type \"$caseType\" is not supported")
  }

  override fun check(node: ASTNode): CasingViolation? {
    if (node !is VariableDeclarationNode) {
      return null
    }
    return check(node)
  }

  private fun check(node: VariableDeclarationNode): CasingViolation? {
    return if (!case.check(node.variable)) CasingViolation(node.position, caseType) else null
  }
}
