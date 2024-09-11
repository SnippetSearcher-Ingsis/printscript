package com.printscript.formatter.rule

data class InlineIfBraceRule(val active: Boolean) : FormatRule {
  override fun apply(): String {
    return (if (active) " " else "\n") + "{\n"
  }
}
