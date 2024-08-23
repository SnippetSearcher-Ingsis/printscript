import builder.AnalyzeBuilder
import builder.ExecuteBuilder
import builder.FormatBuilder
import builder.VerifyBuilder

class CLI {

  private val commandToBuilder = mapOf(
    "execute" to { command: List<String> -> ExecuteBuilder().build(command.drop(1)) },
    "analyze" to { command: List<String> -> AnalyzeBuilder().build(command.drop(1)) },
    "format" to { command: List<String> -> FormatBuilder().build(command.drop(1)) },
    "verify" to { command: List<String> -> VerifyBuilder().build(command.drop(1)) },
  )

  fun executeFile(filePath: String) {
    val commandsSource = TXTHandler.content("/commands/$filePath")
    if (commandsSource == "") {
      println("File $filePath not found")
      return
    }
    val commands = splitLine(commandsSource)
    commands.forEach { command ->
      val commandPair = command.split(" ")
      val result = sendCommand(commandPair)
      println("COMMAND: ${command.uppercase()}")
      when {
        result == null -> println("Can't do action ${commandPair[0]}")
        result.error == "" -> result.output.forEach { println(it) }
        else -> println("Error: " + result.error)
      }
      println("\n")
    }
  }

  private fun splitLine(fileToString: String): List<String> {
    return fileToString.split("\n").filter { it != "" }
  }

  private fun sendCommand(parameter: List<String>): Result? {
    return commandToBuilder[parameter[0]]?.invoke(parameter)
  }
}
