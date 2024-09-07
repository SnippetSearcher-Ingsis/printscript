package com.printscript.formatter

import com.printscript.formatter.rule.IndentRule
import com.printscript.formatter.rule.LineBreaksBeforePrints
import com.printscript.formatter.rule.SpaceAroundColons
import com.printscript.formatter.rule.SpaceAroundEquals

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
