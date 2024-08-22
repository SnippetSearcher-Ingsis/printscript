package logger

class Logger : ILogger {
  private val log = mutableListOf<String>()

  override fun getLog(): List<String> = log

  override fun log(message: String) {
    log.add(message)
  }
}
