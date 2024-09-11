package com.printscript.formatter

import com.google.gson.annotations.SerializedName
import com.printscript.formatter.rule.IndentRule
import com.printscript.formatter.rule.InlineIfBraceRule
import com.printscript.formatter.rule.LineBreaksRule
import com.printscript.formatter.rule.SpaceAroundColonsRule
import com.printscript.formatter.rule.SpaceAroundEqualsRule

data class FormatterConfig(
  @SerializedName("line-breaks-before-println") val lineBreaksBeforePrints: Int?,
  @SerializedName("line-breaks-after-println") val lineBreaksAfterPrints: Int?,
  @SerializedName("enforce-spacing-around-equals") val spaceAroundEquals: Boolean?,
  @SerializedName("enforce-no-spacing-around-equals") val noSpaceAroundEquals: Boolean?,
  @SerializedName("enforce-spacing-before-colon-in-declaration") val spaceBeforeColon: Boolean?,
  @SerializedName("enforce-spacing-after-colon-in-declaration") val spaceAfterColon: Boolean?,
  @SerializedName("if-brace-same-line") val ifBraceSameLine: Boolean?,
  @SerializedName("if-brace-below-line") val ifBraceBelowLine: Boolean?,
  @SerializedName("indent-inside-if") val indent: Int?
) {
  val lineBreaksBeforePrintsRule: LineBreaksRule
    get() = LineBreaksRule(lineBreaksBeforePrints ?: 0)

  val lineBreaksAfterPrintsRule: LineBreaksRule
    get() = LineBreaksRule(lineBreaksAfterPrints ?: 0)

  val spaceAroundEqualsRule: SpaceAroundEqualsRule
    get() {
      if (spaceAroundEquals == noSpaceAroundEquals && spaceAroundEquals != null) {
        throw IllegalArgumentException(
          "Illegal formatting parameters, 'enforce-spacing-around-equals and' " +
            "'enforce-no-spacing-around-equals' should be different"
        )
      }
      return SpaceAroundEqualsRule(spaceAroundEquals ?: true && !(noSpaceAroundEquals ?: false))
    }

  val spaceAroundColonsRule: SpaceAroundColonsRule
    get() = SpaceAroundColonsRule(spaceBeforeColon ?: false, spaceAfterColon ?: true)

  val indentRule: IndentRule
    get() = IndentRule(indent ?: 2)

  val inlineIfBraceRule: InlineIfBraceRule
    get() {
      if (ifBraceSameLine == ifBraceBelowLine && ifBraceSameLine != null) {
        throw IllegalArgumentException(
          "Illegal formatting parameters, 'if-brace-same-line' and 'if-brace-below-line' should be different"
        )
      }
      return InlineIfBraceRule(ifBraceSameLine ?: true && !(ifBraceBelowLine ?: false))
    }
}
