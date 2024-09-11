package com.printscript.formatter

import com.google.gson.Gson
import java.io.File

data object Formatter {
  fun format(node: com.printscript.models.node.ASTNode, json: File): String {
    val config = Gson().fromJson(json.readText(Charsets.UTF_8), FormatterConfig::class.java)
    if (config.lineBreaksBeforePrints != null && (config.lineBreaksBeforePrints < 0 || config.lineBreaksBeforePrints > 2)) {
      throw IllegalArgumentException(
        (
          "Number of line breaks before print statements must be between 0 and 2, " +
            "${config.lineBreaksBeforePrintsRule} was provided"
          )
      )
    }
    if (config.lineBreaksAfterPrints != null && (config.lineBreaksAfterPrints < 0 || config.lineBreaksAfterPrints > 2)) {
      throw IllegalArgumentException(
        (
          "Number of line breaks before print statements must be between 0 and 2, " +
            "${config.lineBreaksBeforePrintsRule} was provided"
          )
      )
    }
    val result = StringBuilder()
    val visitor = FormatterVisitor(config, result)
    visitor.evaluate(node)
    return result.toString()
  }
}
