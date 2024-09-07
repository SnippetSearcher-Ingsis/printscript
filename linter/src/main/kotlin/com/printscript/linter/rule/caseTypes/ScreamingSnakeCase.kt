package com.printscript.linter.rule.caseTypes

data object ScreamingSnakeCase : Case {
  override fun check(input: String): Boolean {
    return input.none { it.isLowerCase() || it == '-' }
  }
}
