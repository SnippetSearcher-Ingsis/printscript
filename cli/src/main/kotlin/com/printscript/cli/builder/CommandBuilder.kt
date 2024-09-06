package com.printscript.cli.builder

import com.printscript.cli.Result

interface CommandBuilder {
  fun build(command: List<String>): Result
}
