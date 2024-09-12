package com.printscript.cli.builder

import com.printscript.cli.Result
import com.printscript.cli.commands.Analyze

class AnalyzeBuilder : CommandBuilder {
  override fun build(command: List<String>): Result {
    return when {
      command.size < 2 -> Result("Missing Arguments", emptyList())
      command.size > 2 -> Result("Too Many Arguments", emptyList())
      else -> Analyze().execute(command[0], command[1])
    }
  }
}
