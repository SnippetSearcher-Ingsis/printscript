package com.printscript.formatter.rule

data class SpaceAroundEquals(private val active: Boolean) : FormatRule {
  override fun apply(): String {
    val result = StringBuilder()
    if (active) {
      result.append(" ")
    }
    result.append("=")
    if (active) {
      result.append(" ")
    }
    return result.toString()
  }
}
