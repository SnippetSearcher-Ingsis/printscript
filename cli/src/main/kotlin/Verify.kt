import java.io.File

class Verify () : ActionBuilder{
    override fun build(file: String): Result {
        val result = Execute().build(file)
        return if (result.error.isEmpty()) Result("", listOf("true")) else Result ("", listOf("false"))
    }
}