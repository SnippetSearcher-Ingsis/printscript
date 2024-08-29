import com.google.gson.Gson
import node.ASTNode
import java.io.File

data object Formatter {
  fun format(nodes: List<ASTNode>, json: File): String {
    val config = Gson().fromJson(json.readText(Charsets.UTF_8), FormatterConfig::class.java)
    if (config.lineBreaksBeforePrints < 0 || config.lineBreaksBeforePrints > 2) {
      throw IllegalArgumentException(
        (
          "Number of line breaks before print statements must be between 0 and 2, " +
            "${config.lineBreaksBeforePrintsRule} was provided"
          )
      )
    }
    val result = StringBuilder()
    val visitor = FormatterVisitor(config, result)
    for (node in nodes) {
      visitor.evaluate(node)
    }
    return result.toString()
  }
}
