package rule

class SpaceAroundColons(private val before: Boolean, private val after: Boolean) : ConfigurableRule {
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
