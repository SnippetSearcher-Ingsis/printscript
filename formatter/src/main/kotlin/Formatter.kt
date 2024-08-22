import com.google.gson.Gson
import node.ASTNode
import rule.LineBreaksBeforePrints
import rule.RuleSet
import rule.SpaceAroundColons
import rule.SpaceAroundEquals
import java.io.File

object Formatter {
  fun format(nodes: List<ASTNode>, json: File): String {
    val config = Gson().fromJson(json.readText(Charsets.UTF_8), FormatterConfig::class.java)
    if (config.lineBreaksBeforePrints < 0 || config.lineBreaksBeforePrints > 2) {
      throw IllegalArgumentException(
        (
          "Number of line breaks before print statements must be between 0 and 2, " +
            "${config.lineBreaksBeforePrints} was provided"
          )
      )
    }
    val result = StringBuilder()
    val ruleSet = RuleSet(
      LineBreaksBeforePrints(config.lineBreaksBeforePrints),
      SpaceAroundEquals(config.spaceAroundEquals),
      SpaceAroundColons(config.spaceBeforeColon, config.spaceAfterColon)
    )
    val visitor = FormatterVisitor(ruleSet, result)
    for (node in nodes) {
      node.accept(visitor)
    }
    return result.toString()
  }
}
