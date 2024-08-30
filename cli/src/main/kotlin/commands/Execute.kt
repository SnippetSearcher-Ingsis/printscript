package commands

import Result

class Execute : CommandExecute {
  override fun execute(vararg file: String): Result {
    return Result("", emptyList())
  }
}
