import com.google.gson.Gson
import node.ASTNode
import violation.Violation
import java.io.File

data object Linter {
  fun lint(node: ASTNode, json: File): List<Violation> {
    val config = Gson().fromJson(json.readText(Charsets.UTF_8), LinterConfig::class.java)
    val result: MutableList<Violation> = mutableListOf()
    val evaluator = Evaluator(config, result)
    evaluator.evaluate(node)
    return result
  }
}
