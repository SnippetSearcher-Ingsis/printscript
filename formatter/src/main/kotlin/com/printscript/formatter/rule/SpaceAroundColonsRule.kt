package com.printscript.formatter.rule

data class SpaceAroundColonsRule(private val before: Boolean, private val after: Boolean) : FormatRule {
  override fun apply(): String {
    val result = StringBuilder()
    if (before) {
      result.append(" ")
    }
    result.append(":")
    if (after) {
      result.append(" ")
    }
    return result.toString()
  }
}
