package com.printscript.cli.commands

import com.printscript.cli.Result

class Verify : CommandExecute {
  override fun execute(vararg file: String): Result {
    val result = Execute().execute(file[0])
    return if (result.error.isEmpty()) Result("", listOf("The code is ready to execute"))
    else Result(result.error, emptyList())
  }
}
