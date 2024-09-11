package com.printscript.cli.builder

import com.printscript.cli.Result
import com.printscript.cli.commands.Format

class FormatBuilder : CommandBuilder {
  override fun build(command: List<String>): Result {
    return when {
      command.size < 2 -> Result("Missing Arguments", emptyList())
      command.size > 2 -> Result("Too Many Arguments", emptyList())
      else -> Format().execute(command[0], command[1])
    }
  }
}
