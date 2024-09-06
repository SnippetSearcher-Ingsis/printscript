package com.printscript.cli.builder

import com.printscript.cli.Result
import com.printscript.cli.commands.Verify

class VerifyBuilder : CommandBuilder {
  override fun build(command: List<String>): Result {
    if (command.isEmpty()) {
      return Result("Missing Arguments", emptyList())
    } else if (command.size > 1) {
      return Result("Too Many Arguments", emptyList())
    }
    return Verify().execute(command[0])
  }
}
