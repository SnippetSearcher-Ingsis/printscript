package rule.caseTypes

sealed interface Case {
  fun check(input: String): Boolean
}
