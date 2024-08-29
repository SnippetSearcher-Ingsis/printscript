package rule

sealed interface ConfigurableRule {
  fun apply(): String
}
