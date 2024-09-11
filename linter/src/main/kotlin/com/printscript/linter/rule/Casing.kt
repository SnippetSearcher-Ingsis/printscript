package com.printscript.linter.rule

import com.printscript.linter.rule.caseTypes.CamelCase
import com.printscript.linter.rule.caseTypes.Case
import com.printscript.linter.rule.caseTypes.KebabCase
import com.printscript.linter.rule.caseTypes.PascalCase
import com.printscript.linter.rule.caseTypes.ScreamingKebabCase
import com.printscript.linter.rule.caseTypes.ScreamingSnakeCase
import com.printscript.linter.rule.caseTypes.SnakeCase
import com.printscript.linter.violation.CasingViolation

data class Casing(private val caseType: String?) : LintRule {
  private val case: Case? = when (caseType) {
    "camel case" -> CamelCase
    "pascal case" -> PascalCase
    "snake case" -> SnakeCase
    "screaming snake case" -> ScreamingSnakeCase
    "kebab case" -> KebabCase
    "screaming kebab case" -> ScreamingKebabCase
    null -> null
    else -> throw IllegalArgumentException("Case type \"$caseType\" is not supported")
  }

  override fun check(node: com.printscript.models.node.ASTNode): CasingViolation? {
    return if (node !is com.printscript.models.node.VariableDeclarationNode || case == null) null else check(node)
  }

  private fun check(node: com.printscript.models.node.VariableDeclarationNode): CasingViolation? {
    return if (!case!!.check(node.identifier)) CasingViolation(node.position, caseType!!) else null
  }
}
