import com.google.gson.Gson
import node.ASTNode
import rule.LineBreakAfterSemicolon
import rule.LineBreaksBeforePrints
import rule.SpaceAroundColons
import rule.SpaceAroundEquals
import rule.SpaceBetweenOperators
import rule.SpaceBetweenTokens
import java.io.File

data class MyConfig(
  val lineBreaksBeforePrints: Int,
  val spaceAroundEquals: Boolean,
  val spaceBeforeColon: Boolean,
  val spaceAfterColon: Boolean,
)

class Formatter {
<<<<<<< HEAD
  fun format(nodes: List<ASTNode>): String {
    val file = File("formatter/src/main/resources/si.json").readText(Charsets.UTF_8)
    val gson = Gson()
    val config = gson.fromJson(file, MyConfig::class.java)
    val result = StringBuilder()
    val rules = listOf(
      LineBreaksBeforePrints(config.lineBreaksBeforePrints),
      SpaceAroundEquals(config.spaceAroundEquals),
      SpaceAroundColons(config.spaceBeforeColon, config.spaceAfterColon),
      LineBreakAfterSemicolon(),
      SpaceBetweenOperators(),
      SpaceBetweenTokens()
    )
    val visitor = FormatterVisitor(rules, result)
    TODO()
  }
}
=======
    fun format(nodes: List<ASTNode>): String {
        val file = File("formatter/src/main/resources/config.json").readText(Charsets.UTF_8)
        val gson = Gson()
        val config = gson.fromJson(file, MyConfig::class.java)
        val result = StringBuilder()
        val ruleSet = RuleSet(LineBreaksBeforePrints(config.lineBreaksBeforePrints),
                              SpaceAroundEquals(config.spaceAroundEquals),
                              SpaceAroundColons(config.spaceBeforeColon, config.spaceAfterColon))
        for (node in nodes) {
            val currentStatement = StringBuilder()
            val currentVisitor = FormatterVisitor(ruleSet, result)
            node.accept(currentVisitor)
            result.append(currentStatement.toString())
        }
        return result.toString()
    }
}
>>>>>>> 736857229d1a6447f2d70635b2c775520d322df5
