import com.google.gson.Gson
import node.ASTNode
import rule.Casing
import rule.NoExpressionsInsidePrint
import violation.Violation
import java.io.File

data class MyConfig(val casing: String, val noExpressionInsidePrint: Boolean)

class Linter {
  fun lint(nodes: List<ASTNode>): List<Violation> {
    val file = File("linter/src/main/resources/si.json").readText(Charsets.UTF_8)
    val gson = Gson()
    val config = gson.fromJson(file, MyConfig::class.java)
    val result: MutableList<Violation> = mutableListOf()
    val rules = listOf(
      Casing(config.casing),
      NoExpressionsInsidePrint(config.noExpressionInsidePrint)
    )
    val evaluator = Evaluator(rules, result)
    for (node in nodes) {
      evaluator.evaluate(node)
    }
    return result
  }
}
