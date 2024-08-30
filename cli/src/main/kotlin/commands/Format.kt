package commands

import Result

class Format : CommandExecute {
  override fun execute(vararg file: String): Result {
    return Result("", emptyList())
  }
}
