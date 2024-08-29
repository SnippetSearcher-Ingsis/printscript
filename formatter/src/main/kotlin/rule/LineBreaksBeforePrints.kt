package rule

data class LineBreaksBeforePrints(private val lineBreaks: Int) : FormatRule {
  override fun apply(): String {
    return "\n".repeat(lineBreaks)
  }
}
