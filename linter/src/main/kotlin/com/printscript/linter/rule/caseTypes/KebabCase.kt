package com.printscript.linter.rule.caseTypes

object KebabCase : Case {
  override fun check(input: String): Boolean {
    return input.none { it.isUpperCase() || it == '_' }
  }
}
