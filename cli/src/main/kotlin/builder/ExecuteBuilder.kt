package builder

import Result
import commands.Execute

class ExecuteBuilder : CommandBuilder {
  override fun build(command: List<String>): Result {
    if (command.isEmpty()) {
      return Result("Missing Arguments", emptyList())
    } else if (command.size > 1) {
      return Result("Too Many Arguments", emptyList())
    }
    return Execute().execute(command[0])
  }
}
