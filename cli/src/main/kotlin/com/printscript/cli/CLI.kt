package com.printscript.cli

import com.printscript.cli.builder.AnalyzeBuilder
import com.printscript.cli.builder.ExecuteBuilder
import com.printscript.cli.builder.FormatBuilder
import com.printscript.cli.builder.VerifyBuilder

class CLI {

  private val commandToBuilder = mapOf(
    "execute" to { command: List<String> -> ExecuteBuilder().build(command.drop(1)) },
    "analyze" to { command: List<String> -> AnalyzeBuilder().build(command.drop(1)) },
    "format" to { command: List<String> -> FormatBuilder().build(command.drop(1)) },
    "verify" to { command: List<String> -> VerifyBuilder().build(command.drop(1)) },
  )

  fun executeFile(filePath: String) {
    val commandsSource = TXTHandler.content("/commands/$filePath")
    if (commandsSource == null) {
      println("File $filePath not found")
      return
    }
    val commands = commandsSource.buffered()
    var currentLine = commands.readLine()
    while (currentLine != null) {
      println("COMMAND: ${currentLine.uppercase()}")
      runCommand(currentLine)
      currentLine = commands.readLine()
    }
  }

  fun singleCommand(command: String) {
    runCommand(command)
  }

  private fun runCommand(command: String) {
    val commandPair = command.split(" ")
    val result = sendCommand(commandPair)
    when {
      result == null -> println("Can't do action ${commandPair[0]}")
      result.error == "" -> result.output.forEach { println(it) }
      else -> println("Error: " + result.error)
    }
    println("\n")
  }

  private fun sendCommand(parameter: List<String>): Result? {
    return commandToBuilder[parameter[0]]?.invoke(parameter)
  }
}
