package rule

class LineBreaksBeforePrints(private val lineBreaks: Int) : ConfigurableRule {
  fun apply(): String {
    return "\n".repeat(lineBreaks)
  }
}
