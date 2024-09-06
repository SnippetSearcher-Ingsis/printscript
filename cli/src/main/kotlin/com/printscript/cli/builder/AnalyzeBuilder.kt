package com.printscript.cli.builder

import com.printscript.cli.Result
import com.printscript.cli.commands.Analyze

class AnalyzeBuilder : CommandBuilder {
  override fun build(command: List<String>): Result {
    if (command.size <= 1) {
      return Result("Missing Arguments", emptyList())
    } else if (command.size > 2) {
      return Result("Too Many Arguments", emptyList())
    }
    return Analyze().execute(command[0], command[1])
  }
}
