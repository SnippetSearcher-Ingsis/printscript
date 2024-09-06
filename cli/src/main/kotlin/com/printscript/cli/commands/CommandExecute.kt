package com.printscript.cli.commands

import com.printscript.cli.Result

interface CommandExecute {
  fun execute(vararg file: String): Result
}
