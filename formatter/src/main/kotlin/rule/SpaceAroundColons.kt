package rule

class SpaceAroundColons(val before: Boolean, val after: Boolean) : Rule {
    fun apply() : String {
        val result = StringBuilder()
        if (before) {
            result.append(" ")
        }
        result.append(":")
        if (after) {
            result.append(" ")
        }
        return result.toString()
    }
}