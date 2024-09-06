package com.printscript.formatter.rule

sealed interface FormatRule {
  fun apply(): String
}
