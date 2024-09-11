package com.printscript.linter.rule.caseTypes

data object SnakeCase : Case {
  override fun check(input: String): Boolean {
    return input.none { it.isUpperCase() || it == '-' }
  }
}
