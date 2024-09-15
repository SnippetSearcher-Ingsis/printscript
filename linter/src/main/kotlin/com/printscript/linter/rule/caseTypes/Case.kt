package com.printscript.linter.rule.caseTypes

interface Case {
  fun check(input: String): Boolean
}
