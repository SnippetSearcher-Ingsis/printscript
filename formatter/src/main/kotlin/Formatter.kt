import com.google.gson.Gson
import node.ASTNode
import rule.LineBreaksBeforePrints
import rule.RuleSet
import rule.SpaceAroundColons
import rule.SpaceAroundEquals
import java.io.File

data class MyConfig(
  val lineBreaksBeforePrints: Int,
  val spaceAroundEquals: Boolean,
  val spaceBeforeColon: Boolean,
  val spaceAfterColon: Boolean,
)

class Formatter {
  fun format(nodes: List<ASTNode>): String {
    val file = File("formatter/src/main/resources/config.json").readText(Charsets.UTF_8)
    val gson = Gson()
    val config = gson.fromJson(file, MyConfig::class.java)
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
