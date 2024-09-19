package com.printscript.linter.rule.caseTypes

object ScreamingKebabCase : Case {
  override fun check(input: String): Boolean {
    return input.none { it.isLowerCase() || it == '_' }
  }
}
