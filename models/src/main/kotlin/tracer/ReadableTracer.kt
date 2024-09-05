package tracer

class ReadableTracer : Tracer {
  private val output = mutableListOf<String>()

  override fun print(message: String) {
    output.add(message)
  }

  fun getOutput(): List<String> = output
}
