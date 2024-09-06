package com.printscript.models.node

data class Position(val line: Int, val column: Int) {
  override fun toString(): String {
    return "$line:$column"
  }

  override fun equals(other: Any?): Boolean {
    return when {
      other is Position -> line == other.line && column == other.column
      else -> super.equals(other)
    }
  }

  override fun hashCode(): Int {
    var result = line
    result = 31 * result + column
    return result
  }
}
