import com.google.gson.Gson
import node.ASTNode
import rule.*
import java.io.File

data class MyConfig(val lineBreaksBeforePrints: Int,
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