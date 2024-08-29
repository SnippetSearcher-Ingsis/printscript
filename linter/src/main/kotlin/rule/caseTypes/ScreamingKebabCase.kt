package rule.caseTypes

data object ScreamingKebabCase : Case {
  override fun check(input: String): Boolean {
    return input.none { it.isLowerCase() || it == '_' }
  }
}
