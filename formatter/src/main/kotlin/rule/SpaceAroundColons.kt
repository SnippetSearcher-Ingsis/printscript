package rule

<<<<<<< HEAD
class SpaceAroundColons(val before: Boolean, val after: Boolean) : Rule
=======
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
>>>>>>> 736857229d1a6447f2d70635b2c775520d322df5
