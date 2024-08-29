package rule

sealed interface FormatRule {
  fun apply(): String
}
