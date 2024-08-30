package commands

import Result

class Analyze : CommandExecute {
  override fun execute(vararg file: String): Result {
    return Result("", emptyList())
  }
}
