import java.io.File

data class Result(val error: String, val output: List< String >)

interface ActionBuilder {

  fun build(file: File): Result
}
