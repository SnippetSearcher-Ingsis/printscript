import com.google.gson.Gson
import node.ASTNode
import rule.LineBreaksBeforePrints
import rule.RuleSet
import rule.SpaceAroundColons
import rule.SpaceAroundEquals
import java.io.File

class Formatter {
  fun format(nodes: List<ASTNode>, fileToRead: File): String {
    val file = fileToRead.readText(Charsets.UTF_8)
    val gson = Gson()
    val config = gson.fromJson(file, Config::class.java)
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
