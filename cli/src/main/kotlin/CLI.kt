import java.io.File

class CLI {

    private val commandToBuilder = mapOf(
        "execute" to { file : File -> Execute().build(file) },
        "analyze" to { file : File -> Analyze().build(file) },
        "format" to { file : File -> Format().build(file) },
        "verify" to { file : File -> Verify().build(file) },

    )

    fun executeFile (fileToString : String) {
        val commandPath = splitLine(fileToString)
        commandPath.forEach { (command, path) ->
            print("Executing $command with $path")

            val result = sendCommand(Pair(command, path))

            when {
                result == null -> print("Can't do action $command")
                result.error == "" -> {
                    print("Execution was successful :)")
                    print("This execution response was : ${result.output}")
                }
                else -> {
                    print("Execution failed :( ")
                    print(result.error)
                }
            }
        }
    }

    private fun splitLine (fileToString: String) : List<Pair<String, String>> {
        val commands =  fileToString.split("\n")
        return commands.mapNotNull { command ->
            val split = command.split(" ").filter { it.isNotBlank() }
            when (split.size) {
                2 -> Pair(split[0], split[1])
                else -> null
            }
        }
    }

    private fun sendCommand (command : Pair<String, String>) : Result? {
        val (action, path) = command
        return commandToBuilder[action]?.invoke(File(path))
    }


}