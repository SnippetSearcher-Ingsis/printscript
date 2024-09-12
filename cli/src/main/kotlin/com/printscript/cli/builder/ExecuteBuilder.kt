package com.printscript.cli.builder

import com.printscript.cli.Result
import com.printscript.cli.commands.Execute

class ExecuteBuilder : CommandBuilder {
  override fun build(command: List<String>): Result {
    return when {
      command.isEmpty() -> Result("Missing Arguments", emptyList())
      command.size > 1 -> Result("Too Many Arguments", emptyList())
      else -> Execute().execute(command[0])
    }
  }
}
