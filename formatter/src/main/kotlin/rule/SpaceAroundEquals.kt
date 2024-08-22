package rule

class SpaceAroundEquals(val active: Boolean) : Rule {
  fun apply(): String {
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
