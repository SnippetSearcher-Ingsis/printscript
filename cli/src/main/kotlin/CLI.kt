import java.io.File

class CLI {

  private val commandToBuilder = mapOf(
    "execute" to { file: String -> Execute().build(file) },
    "analyze" to { file: String -> Analyze().build(file) },
    "format" to { file: String -> Format().build(file) },
    "verify" to { file: String -> Verify().build(file) },

  )

  fun executeFile(fileToString: String) {
    val stringify  = TXTHandler.content(fileToString)
    val commandPath = splitLine(stringify)
    commandPath.forEach { (command, path) ->
      println("Executing $command with $path")

      val result = sendCommand(Pair(command, path))

      when {
        result == null -> print("Can't do action $command")
        result.error == "" -> {
          println("Execution was successful :)")
          println("This execution response was : ${result.output}")
        }
        else -> {
          println("Execution failed :( ")
          println(result.error)
        }
      }
    }
  }

  private fun splitLine(fileToString: String): List<Pair<String, String>> {

    val commands = fileToString.split("\n")
    return commands.mapNotNull { command ->
      val split = command.split(" ").filter { it.isNotBlank() }
      when (split.size) {
        2 -> Pair(split[0], split[1])
        else -> null
      }
    }
  }

  private fun sendCommand(command: Pair<String, String>): Result? {
    val (action, path) = command
    return commandToBuilder[action]?.invoke(path)
  }
}
