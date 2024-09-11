package com.printscript.formatter.rule

data class LineBreaksRule(private val lineBreaks: Int) : FormatRule {
  override fun apply(): String {
    return "\n".repeat(lineBreaks)
  }
}
