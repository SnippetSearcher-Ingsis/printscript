package com.printscript.linter.rule.caseTypes

data object PascalCase : Case {
  override fun check(input: String): Boolean {
    return input[0].isUpperCase() && !input.contains("_")
  }
}
