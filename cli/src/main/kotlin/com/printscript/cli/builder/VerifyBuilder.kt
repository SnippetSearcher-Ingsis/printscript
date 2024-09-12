package com.printscript.cli.builder

import com.printscript.cli.Result
import com.printscript.cli.commands.Verify

class VerifyBuilder : CommandBuilder {
  override fun build(command: List<String>): Result {
    return when {
      command.isEmpty() -> Result("Missing Arguments", emptyList())
      command.size > 1 -> Result("Too Many Arguments", emptyList())
      else -> Verify().execute(command[0])
    }
  }
}
