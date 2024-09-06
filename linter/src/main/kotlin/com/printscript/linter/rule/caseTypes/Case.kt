package com.printscript.linter.rule.caseTypes

sealed interface Case {
  fun check(input: String): Boolean
}
