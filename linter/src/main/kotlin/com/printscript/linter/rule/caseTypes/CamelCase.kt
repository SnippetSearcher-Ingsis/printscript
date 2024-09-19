package com.printscript.linter.rule.caseTypes

object CamelCase : Case {
  override fun check(input: String): Boolean {
    return input[0].isLowerCase() && !input.contains("_")
  }
}
