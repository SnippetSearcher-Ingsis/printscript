package commands

import Result

interface CommandExecute {
  fun execute(vararg file: String): Result
}
