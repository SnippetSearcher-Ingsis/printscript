package com.printscript.formatter.rule

data class IndentRule(val indentSize: Int) : FormatRule {
  override fun apply(): String {
    return " ".repeat(indentSize)
  }
}
