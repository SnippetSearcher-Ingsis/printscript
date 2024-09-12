package com.printscript.formatter

import com.google.gson.Gson
import java.io.File

data object Formatter {
  fun format(node: com.printscript.models.node.ASTNode, json: File): String {
    val config = Gson().fromJson(json.readText(Charsets.UTF_8), FormatterConfig::class.java)
    checkConfig(config)
    val result = StringBuilder()
    val visitor = FormatterVisitor(config, result)
    visitor.evaluate(node)
    return result.toString()
  }

  private fun checkConfig(config: FormatterConfig) {
    if (config.ifBraceSameLine == config.ifBraceBelowLine && config.ifBraceSameLine != null) {
      throw IllegalArgumentException(
        "Illegal formatting parameters, 'if-brace-same-line' and 'if-brace-below-line' should be different"
      )
    }
    if (config.spaceAroundEquals == config.noSpaceAroundEquals && config.spaceAroundEquals != null) {
      throw IllegalArgumentException(
        "Illegal formatting parameters, 'enforce-spacing-around-equals and' " +
          "'enforce-no-spacing-around-equals' should be different"
      )
    }
    if (config.lineBreaksAfterPrints != null && (config.lineBreaksAfterPrints < 0 || config.lineBreaksAfterPrints > 2)) {
      throw IllegalArgumentException(
        "Number of line breaks after print statements must be between 0 and 2, " +
          "${config.lineBreaksAfterPrints} was provided"
      )
    }
  }
}
