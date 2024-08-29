package rule

data class LineBreaksBeforePrints(private val lineBreaks: Int) : ConfigurableRule {
  override fun apply(): String {
    return "\n".repeat(lineBreaks)
  }
}
