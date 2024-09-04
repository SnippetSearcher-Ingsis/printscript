import rule.IndentRule
import rule.LineBreaksBeforePrints
import rule.SpaceAroundColons
import rule.SpaceAroundEquals

data class FormatterConfig(
  val lineBreaksBeforePrints: Int,
  val spaceAroundEquals: Boolean,
  val spaceBeforeColon: Boolean,
  val spaceAfterColon: Boolean,
  val indent: Int
) {
  val lineBreaksBeforePrintsRule: LineBreaksBeforePrints
    get() = LineBreaksBeforePrints(lineBreaksBeforePrints)

  val spaceAroundEqualsRule: SpaceAroundEquals
    get() = SpaceAroundEquals(spaceAroundEquals)

  val spaceAroundColonsRule: SpaceAroundColons
    get() = SpaceAroundColons(spaceBeforeColon, spaceAfterColon)

  val indentRule: IndentRule
    get() = IndentRule(indent)
}
