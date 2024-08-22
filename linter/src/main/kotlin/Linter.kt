import com.google.gson.Gson
import node.ASTNode
import rule.Casing
import rule.NoExpressionsInsidePrint
import violation.Violation
import java.io.File

object Linter {
  fun lint(nodes: List<ASTNode>, json: File): List<Violation> {
    val config = Gson().fromJson(json.readText(Charsets.UTF_8), LinterConfig::class.java)
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
