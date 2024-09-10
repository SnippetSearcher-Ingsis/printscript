package com.printscript.linter.rule.caseTypes

data object AnyCase : Case {
  override fun check(input: String): Boolean = true
}
